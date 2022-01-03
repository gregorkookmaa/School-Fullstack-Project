import { AbstractControl, FormGroup, ValidatorFn } from '@angular/forms';

/**
 * Validates if control value starts with **"NO"**
 */
export function indexValidator(): ValidatorFn {
	return (control: AbstractControl): { [key: string]: boolean } | null => {
		const valueAsString = String(control.value);

		if (!valueAsString.startsWith('NO')) {
			return { indexStart: true };
		}
		return null;
	};
}

/**
 * Validates if controls value is smaller than the chosen controls value in the form
 * @param maxValueControlName - name of the control whose value is the max energy value
 */
export function maximumEnergyValidator(maxValueControlName: string): ValidatorFn {
	return (control: AbstractControl): { [key: string]: boolean } | null => {
		const form = control.root;
		const maxEnergyControl = form.get(maxValueControlName);
		const max = Number(maxEnergyControl?.value);

		if (!isNaN(max) && control.value > max) {
			return { maxEnergy: true };
		}
		return null;
	};
}
