import { AbstractControl, FormGroup, ValidatorFn } from '@angular/forms';
import { Building } from '../../model/building';

/**
 * Validates if index starts with **"NO"**
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
 * Validates if index is smaller than **max energy** or max !isNan
 * @param max - the maximum energy value
 */
export function maximumEnergyValidator(): ValidatorFn {
	return (control: AbstractControl): { [key: string]: boolean } | null => {
		const form = control.root;
		const maxEnergyControl = form.get('energyUnitMax');
		const max = Number(maxEnergyControl?.value);

		if (!isNaN(max) && control.value > max) {
			return { maxEnergy: true };
		}
		return null;
	};
}
