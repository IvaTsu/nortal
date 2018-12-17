/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { CakeClubTestModule } from '../../../test.module';
import { UserSkillsUpdateComponent } from 'app/entities/user-skills/user-skills-update.component';
import { UserSkillsService } from 'app/entities/user-skills/user-skills.service';
import { UserSkills } from 'app/shared/model/user-skills.model';

describe('Component Tests', () => {
    describe('UserSkills Management Update Component', () => {
        let comp: UserSkillsUpdateComponent;
        let fixture: ComponentFixture<UserSkillsUpdateComponent>;
        let service: UserSkillsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CakeClubTestModule],
                declarations: [UserSkillsUpdateComponent]
            })
                .overrideTemplate(UserSkillsUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UserSkillsUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserSkillsService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new UserSkills(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.userSkills = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new UserSkills();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.userSkills = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
