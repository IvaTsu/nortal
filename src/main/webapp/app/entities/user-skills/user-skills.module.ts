import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CakeClubSharedModule } from 'app/shared';
import { CakeClubAdminModule } from 'app/admin/admin.module';
import {
    UserSkillsComponent,
    UserSkillsDetailComponent,
    UserSkillsUpdateComponent,
    UserSkillsDeletePopupComponent,
    UserSkillsDeleteDialogComponent,
    userSkillsRoute,
    userSkillsPopupRoute
} from './';

const ENTITY_STATES = [...userSkillsRoute, ...userSkillsPopupRoute];

@NgModule({
    imports: [CakeClubSharedModule, CakeClubAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UserSkillsComponent,
        UserSkillsDetailComponent,
        UserSkillsUpdateComponent,
        UserSkillsDeleteDialogComponent,
        UserSkillsDeletePopupComponent
    ],
    entryComponents: [UserSkillsComponent, UserSkillsUpdateComponent, UserSkillsDeleteDialogComponent, UserSkillsDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CakeClubUserSkillsModule {}
