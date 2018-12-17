import {Injectable} from '@angular/core';
import {HttpClient, HttpParams, HttpResponse} from '@angular/common/http';
import {Observable, of} from 'rxjs';

import {SERVER_API_URL} from 'app/app.constants';
import {createRequestOption} from 'app/shared/util/request-util';
import {IEvent} from './event.model';

@Injectable({providedIn: 'root'})
export class EventService {
    private resourceUrl = SERVER_API_URL + 'api/event';

    constructor(private http: HttpClient) {
    }

    query(userEvents: boolean): Observable<HttpResponse<IEvent[]>> {

        /* todo: modify to take additional params (number of participants) */

        return this.http.get<IEvent[]>(this.resourceUrl, {observe: 'response', params: new HttpParams().set('userEvents', String(userEvents))});
    }

    find(id: number): Observable<HttpResponse<IEvent>> {
        return this.http.get<IEvent>(`${this.resourceUrl}/${id}`, {observe: 'response'});
    }

    save(event: any): Observable<any> {
        return this.http.post(this.resourceUrl + '/single', event);
    }

    saveMultiple(event: any): Observable<any> {
        return this.http.post(this.resourceUrl + '/multiple', event);
    }

    delete(id: number): Observable<HttpResponse<any>> {

        /* todo: fill body with correct delete request */

        return null;
    }
}
