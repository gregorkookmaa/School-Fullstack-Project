import { AbstractControl } from '@angular/forms';

export function indexValidator(control: AbstractControl): { [key: string]: boolean } | null {
	const valueAsString = String(control.value);

	if (!valueAsString.startsWith('NO')) {
		return { indexStart: true };
	}
	return null;
}
