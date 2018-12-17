import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IUserSkills } from 'app/shared/model/user-skills.model';
import { Principal } from 'app/core';
import { UserSkillsService } from './user-skills.service';

@Component({
    selector: 'jhi-user-skills',
    templateUrl: './user-skills.component.html'
})
export class UserSkillsComponent implements OnInit, OnDestroy {
    userSkills: IUserSkills[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private userSkillsService: UserSkillsService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.userSkillsService.query().subscribe(
            (res: HttpResponse<IUserSkills[]>) => {
                this.userSkills = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInUserSkills();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IUserSkills) {
        return item.id;
    }

    registerChangeInUserSkills() {
        this.eventSubscriber = this.eventManager.subscribe('userSkillsListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
