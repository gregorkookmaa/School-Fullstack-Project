import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Building } from '../model/building';
import { Observable, of, throwError as observableThrowError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
	providedIn: 'root',
})
export class BuildingService {
	private apiUrl = 'api/v1/mega/building';

	constructor(private http: HttpClient) {}

	getAll(): Observable<Building[]> {
		return this.http
		  .get<Building[]>(this.apiUrl)
		  .pipe(map(data => data), catchError(this.handleError))
	}

	get(id: string): Observable<Building> {
		return this.http
		  .get<Building>(`${this.apiUrl}/${id}`)
		  .pipe(map(data => data), catchError(this.handleError))
	}

	post(data: Building) {
		const headers = new Headers();
		headers.append('Content-Type', 'application/json');
		return this.http.post<Building>(this.apiUrl, data).pipe(catchError(this.handleError));
	}

	put(data: Building) {
		const headers = new Headers();
		headers.append('Content-Type', 'application/json');
		return this.http.put<Building>(this.apiUrl, data).pipe(catchError(this.handleError));
	}

	private handleError(res: HttpErrorResponse | any) {
		console.error(res.error || res.body.error);
		return observableThrowError(res.error || 'Server error');
	}
}
