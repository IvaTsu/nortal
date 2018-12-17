import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { UserSkills } from 'app/shared/model/user-skills.model';
import { UserSkillsService } from './user-skills.service';
import { UserSkillsComponent } from './user-skills.component';
import { UserSkillsDetailComponent } from './user-skills-detail.component';
import { UserSkillsUpdateComponent } from './user-skills-update.component';
import { UserSkillsDeletePopupComponent } from './user-skills-delete-dialog.component';
import { IUserSkills } from 'app/shared/model/user-skills.model';

@Injectable({ providedIn: 'root' })
export class UserSkillsResolve implements Resolve<IUserSkills> {
    constructor(private service: UserSkillsService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((userSkills: HttpResponse<UserSkills>) => userSkills.body));
        }
        return of(new UserSkills());
    }
}

export const userSkillsRoute: Routes = [
    {
        path: 'user-skills',
        component: UserSkillsComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserSkills'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-skills/:id/view',
        component: UserSkillsDetailComponent,
        resolve: {
            userSkills: UserSkillsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserSkills'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-skills/new',
        component: UserSkillsUpdateComponent,
        resolve: {
            userSkills: UserSkillsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserSkills'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'user-skills/:id/edit',
        component: UserSkillsUpdateComponent,
        resolve: {
            userSkills: UserSkillsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserSkills'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const userSkillsPopupRoute: Routes = [
    {
        path: 'user-skills/:id/delete',
        component: UserSkillsDeletePopupComponent,
        resolve: {
            userSkills: UserSkillsResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'UserSkills'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
