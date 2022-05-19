import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MainService } from '../../services/main.service';

@Component({
	selector: 'app-main-view',
	templateUrl: './main-view.component.html',
	styleUrls: ['./main-view.component.scss'],
})
export class MainViewComponent implements OnInit {

	constructor(private router: Router, private mainService: MainService) {}

	ngOnInit() {
		
	}

	gotoHumans() {
		this.router.navigate([`humans`]).then();
	}

	gotoBuildings() {
		this.router.navigate(['buildings']).then();
	}
}
