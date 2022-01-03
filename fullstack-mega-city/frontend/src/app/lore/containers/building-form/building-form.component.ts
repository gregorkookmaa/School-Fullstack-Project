import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Building } from '../../model/building';
import { ActivatedRoute, Router } from '@angular/router';
import { BuildingService } from '../../services/building.service';
import { indexValidator, maximumEnergyValidator } from './building-form.component.validators';

@Component({
	selector: 'app-building-form',
	templateUrl: './building-form.component.html',
	styleUrls: ['./building-form.component.scss'],
})
export class BuildingFormComponent implements OnInit {
	building: Building = null;
	form: FormGroup = this.initForm();

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private buildingService: BuildingService,
		private formBuilder: FormBuilder
	) {}

	ngOnInit() {
		let id = this.route.snapshot.paramMap.get('id');

		if (id === 'new') return this.initialize(new Building());

		// At this point forwards **id** should be a number
		if (!Number(id)) return;
		if (id !== null) {
			this.buildingService.get(id).subscribe((data: Building) => {
				this.initialize(data);
			});
		}
	}

	initialize(building?: Building) {
		this.building = building;

		if (building) {
			this.form = this.initForm(building);
		} else {
			// So that energy units are checked when max energy value is changed
			this.form.controls['energyUnitMax'].valueChanges.subscribe(() => {
				this.form.controls['energyUnits'].updateValueAndValidity();
			});
		}
	}

	initForm(building?: Building) {
		return this.formBuilder.group({
			name: new FormControl(
				building?.name || '',
				[Validators.required, Validators.maxLength(50)]
			),
			address: new FormControl(
				building?.address || '',
				[Validators.required, Validators.maxLength(50)]
			),
			index: new FormControl(
				building?.index || '',
				[Validators.required, indexValidator()]
			),
			sectorCode: new FormControl(
				{
					value: building?.sectorCode || '',
					disabled: building?.id,
				}
			),
			energyUnitMax: new FormControl(
				{
					value: building?.energyUnitMax || '',
					disabled: building?.id,
				},
				[Validators.required, Validators.pattern(/^\d+$/)]
			),
			energyUnits: new FormControl(
				building?.energyUnits || '', 
				[Validators.required, Validators.pattern(/^\d+$/), maximumEnergyValidator('energyUnitMax')]
			),
		});
	}

	hasError(path: string, errorCode: string) {
		return this.form && this.form.hasError(errorCode, path);
	}

	navigateToBuildingsList() {
		this.router.navigate(['buildings']).then();
	}

	submit() {
		// getRawValue in order to get the values of disabled controls as well
		const buildingToSave = { ...this.form.getRawValue(), id: this.building.id };
		console.info('Building to save:', buildingToSave);
		if (this.building.id) this.buildingService.put(buildingToSave).subscribe();
		else this.buildingService.post(buildingToSave).subscribe();
	}
}
