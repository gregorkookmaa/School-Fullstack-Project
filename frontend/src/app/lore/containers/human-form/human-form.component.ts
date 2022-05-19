import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Human } from '../../model/human';
import { ActivatedRoute, Router } from '@angular/router';
import { HumanService } from '../../services/human.service';

@Component({
	selector: 'app-human-form',
	templateUrl: './human-form.component.html',
	styleUrls: ['./human-form.component.scss'],
})
export class HumanFormComponent implements OnInit {
	human: Human = null;
	form: FormGroup = this.initForm();

	constructor(
		private router: Router,
		private route: ActivatedRoute,
		private humanService: HumanService,
		private formBuilder: FormBuilder
	) {}

	ngOnInit() {
		let id = this.route.snapshot.paramMap.get('id');

		if (id === 'new') return this.initialize(new Human());

		// At this point forwards **id** should be a number
		if (!Number(id)) return;
		if (id !== null) {
			this.humanService.get(id).subscribe((data: Human) => {
				this.initialize(data);
			});
		}
	}

	initialize(human?: Human) {
		this.human = human;

		if (human?.id) {
			this.form = this.initForm(human);
		} 
	}

	initForm(human?: Human) {
		return this.formBuilder.group({
			name: new FormControl(
				human?.name || '',
				[Validators.required, Validators.maxLength(50)]
			),
			livesin: new FormControl(
				human?.livesin || '',
				[Validators.required, Validators.maxLength(50)]
			),
			gender: new FormControl(
				human?.gender || '',
				[Validators.required, Validators.maxLength(50)]
			),
			age: new FormControl(
				human?.age || '',
				[Validators.required, Validators.maxLength(3)]
			),
		});
	}

	hasError(path: string, errorCode: string) {
		return this.form && this.form.hasError(errorCode, path);
	}

	navigateToHumansList() {
		this.router.navigate(['humans']).then();
	}

	submit() {
		// getRawValue in order to get the values of disabled controls as well
		const humanToSave = { ...this.form.getRawValue(), id: this.human.id };
		console.info('Human to save:', humanToSave);

		if (this.human.id) this.humanService.put(humanToSave).subscribe();
		else this.humanService.post(humanToSave).subscribe();
		this.navigateToHumansList();
	}
}
