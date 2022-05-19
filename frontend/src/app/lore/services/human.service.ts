import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Human } from '../model/human';
import { Observable, of, throwError as observableThrowError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
	providedIn: 'root',
})
export class HumanService {
	private apiUrl = 'api/v1/mega/human';

	constructor(private http: HttpClient) {}

	getAll(): Observable<Human[]> {
		return this.http
		  .get<Human[]>(this.apiUrl)
		  .pipe(map(data => data), catchError(this.handleError))
	}

	get(id: string): Observable<Human> {
		return this.http
		  .get<Human>(`${this.apiUrl}/${id}`)
		  .pipe(map(data => data), catchError(this.handleError))
	}

	post(data: Human) {
		const headers = new Headers();
		headers.append('Content-Type', 'application/json');
		return this.http.post<Human>(this.apiUrl, data).pipe(catchError(this.handleError));
	}

	put(data: Human) {
		const headers = new Headers();
		headers.append('Content-Type', 'application/json');
		return this.http.put<Human>(this.apiUrl, data).pipe(catchError(this.handleError));
	}

	private handleError(res: HttpErrorResponse | any) {
		console.error(res.error || res.body.error);
		return observableThrowError(res.error || 'Server error');
	}
}
