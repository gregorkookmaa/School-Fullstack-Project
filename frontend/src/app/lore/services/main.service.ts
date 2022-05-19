import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, of, throwError as observableThrowError } from 'rxjs';

@Injectable({
	providedIn: 'root',
})
export class MainService {
	private apiUrl = 'api/v1/mega/main';

	constructor(private http: HttpClient) {}

	private handleError(res: HttpErrorResponse | any) {
		console.error(res.error || res.body.error);
		return observableThrowError(res.error || 'Server error');
	}
}
