import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IUserSkills } from 'app/shared/model/user-skills.model';

@Component({
    selector: 'jhi-user-skills-detail',
    templateUrl: './user-skills-detail.component.html'
})
export class UserSkillsDetailComponent implements OnInit {
    userSkills: IUserSkills;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ userSkills }) => {
            this.userSkills = userSkills;
        });
    }

    previousState() {
        window.history.back();
    }
}
