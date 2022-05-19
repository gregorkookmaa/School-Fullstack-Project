import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HumanFormComponent } from './human-form.component';

describe('HumanFormComponent', () => {
	let component: HumanFormComponent;
	let fixture: ComponentFixture<HumanFormComponent>;

	beforeEach(async(() => {
		TestBed.configureTestingModule({
			declarations: [HumanFormComponent],
		}).compileComponents();
	}));

	beforeEach(() => {
		fixture = TestBed.createComponent(HumanFormComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
