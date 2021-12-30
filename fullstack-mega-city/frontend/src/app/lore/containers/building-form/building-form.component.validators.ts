import { AbstractControl, ValidatorFn } from '@angular/forms';

/**
 * Validates if index starts with **"NO"**
 */
export function indexValidator(control: AbstractControl): { [key: string]: boolean } | null {
	const valueAsString = String(control.value);

	if (!valueAsString.startsWith('NO')) {
		return { indexStart: true };
	}
	return null;
}

/**
 * Validates if index is smaller than **max energy** or max !isNan
 * @param max - the maximum energy value
 */
export function maximumEnergyValidator(max: number): ValidatorFn {
	return (control: AbstractControl): { [key: string]: boolean } | null => {
		// Max value can be undefined
		if (!isNaN(max) && control.value > max) {
			return { maxEnergy: true };
		}
		return null;
	};
}
