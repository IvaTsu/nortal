/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { CakeClubTestModule } from '../../../test.module';
import { UserSkillsDeleteDialogComponent } from 'app/entities/user-skills/user-skills-delete-dialog.component';
import { UserSkillsService } from 'app/entities/user-skills/user-skills.service';

describe('Component Tests', () => {
    describe('UserSkills Management Delete Component', () => {
        let comp: UserSkillsDeleteDialogComponent;
        let fixture: ComponentFixture<UserSkillsDeleteDialogComponent>;
        let service: UserSkillsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CakeClubTestModule],
                declarations: [UserSkillsDeleteDialogComponent]
            })
                .overrideTemplate(UserSkillsDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UserSkillsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserSkillsService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
