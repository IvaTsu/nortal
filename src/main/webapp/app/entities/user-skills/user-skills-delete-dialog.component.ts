import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IUserSkills } from 'app/shared/model/user-skills.model';
import { UserSkillsService } from './user-skills.service';

@Component({
    selector: 'jhi-user-skills-delete-dialog',
    templateUrl: './user-skills-delete-dialog.component.html'
})
export class UserSkillsDeleteDialogComponent {
    userSkills: IUserSkills;

    constructor(private userSkillsService: UserSkillsService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userSkillsService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'userSkillsListModification',
                content: 'Deleted an userSkills'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-skills-delete-popup',
    template: ''
})
export class UserSkillsDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ userSkills }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(UserSkillsDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.userSkills = userSkills;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
