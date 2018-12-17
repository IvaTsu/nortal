import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUserSkills } from 'app/shared/model/user-skills.model';
import { UserSkillsService } from './user-skills.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-user-skills-update',
    templateUrl: './user-skills-update.component.html'
})
export class UserSkillsUpdateComponent implements OnInit {
    userSkills: IUserSkills;
    isSaving: boolean;

    users: IUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private userSkillsService: UserSkillsService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userSkills }) => {
            this.userSkills = userSkills;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.userSkills.id !== undefined) {
            this.subscribeToSaveResponse(this.userSkillsService.update(this.userSkills));
        } else {
            this.subscribeToSaveResponse(this.userSkillsService.create(this.userSkills));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUserSkills>>) {
        result.subscribe((res: HttpResponse<IUserSkills>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }
}
