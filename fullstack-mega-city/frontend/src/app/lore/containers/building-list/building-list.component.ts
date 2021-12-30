import { Component, OnInit } from '@angular/core';
import { Building } from '../../model/building';
import { Router } from '@angular/router';
import { BuildingService } from '../../services/building.service';

@Component({
	selector: 'app-building-list',
	templateUrl: './building-list.component.html',
	styleUrls: ['./building-list.component.scss'],
})
export class BuildingListComponent implements OnInit {
	displayedColumns: string[] = ['id', 'name', 'address', 'sectorCode', 'actions'];
	dataSource: Building[] = [];

	constructor(private router: Router, private buildingService: BuildingService) {}

	ngOnInit() {
		this.buildingService.getAll().subscribe(buildings => this.dataSource = buildings);
	}
}
