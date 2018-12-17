/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { CakeClubTestModule } from '../../../test.module';
import { UserSkillsComponent } from 'app/entities/user-skills/user-skills.component';
import { UserSkillsService } from 'app/entities/user-skills/user-skills.service';
import { UserSkills } from 'app/shared/model/user-skills.model';

describe('Component Tests', () => {
    describe('UserSkills Management Component', () => {
        let comp: UserSkillsComponent;
        let fixture: ComponentFixture<UserSkillsComponent>;
        let service: UserSkillsService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CakeClubTestModule],
                declarations: [UserSkillsComponent],
                providers: []
            })
                .overrideTemplate(UserSkillsComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(UserSkillsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UserSkillsService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new UserSkills(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.userSkills[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
