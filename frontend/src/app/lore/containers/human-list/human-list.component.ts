import { Component, OnInit } from '@angular/core';
import { Human } from '../../model/human';
import { Router } from '@angular/router';
import { HumanService } from '../../services/human.service';

@Component({
	selector: 'app-human-list',
	templateUrl: './human-list.component.html',
	styleUrls: ['./human-list.component.scss'],
})
export class HumanListComponent implements OnInit {
	displayedColumns: string[] = ['id', 'name', 'livesin', 'gender', 'age', 'actions'];
	dataSource: Human[] = [];

	constructor(private router: Router, private humanService: HumanService) {}

	ngOnInit() {
		this.humanService.getAll().subscribe(humans => this.dataSource = humans);
	}

	updateHuman(id: number) {
		this.router.navigate([`humans/${id}`]).then();
	}

	createHuman() {
		this.router.navigate(['humans/new']).then();
	}

	goBuildings() {
		this.router.navigate(['/buildings']).then();
	}

	goHome() {
		this.router.navigate(['/main']).then();
	}
}
