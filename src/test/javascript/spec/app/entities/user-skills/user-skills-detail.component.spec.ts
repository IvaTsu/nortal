/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CakeClubTestModule } from '../../../test.module';
import { UserSkillsDetailComponent } from 'app/entities/user-skills/user-skills-detail.component';
import { UserSkills } from 'app/shared/model/user-skills.model';

describe('Component Tests', () => {
    describe('UserSkills Management Detail Component', () => {
        let comp: UserSkillsDetailComponent;
        let fixture: ComponentFixture<UserSkillsDetailComponent>;
        const route = ({ data: of({ userSkills: new UserSkills(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [CakeClubTestModule],
                declarations: [UserSkillsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UserSkillsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UserSkillsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.userSkills).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
