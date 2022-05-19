import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HumanListComponent } from './human-list.component';

describe('HumanListComponent', () => {
	let component: HumanListComponent;
	let fixture: ComponentFixture<HumanListComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [HumanListComponent],
		}).compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(HumanListComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
