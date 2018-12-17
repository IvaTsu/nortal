import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUserSkills } from 'app/shared/model/user-skills.model';

type EntityResponseType = HttpResponse<IUserSkills>;
type EntityArrayResponseType = HttpResponse<IUserSkills[]>;

@Injectable({ providedIn: 'root' })
export class UserSkillsService {
    private resourceUrl = SERVER_API_URL + 'api/user-skills';

    constructor(private http: HttpClient) {}

    create(userSkills: IUserSkills): Observable<EntityResponseType> {
        return this.http.post<IUserSkills>(this.resourceUrl, userSkills, { observe: 'response' });
    }

    update(userSkills: IUserSkills): Observable<EntityResponseType> {
        return this.http.put<IUserSkills>(this.resourceUrl, userSkills, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IUserSkills>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IUserSkills[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
