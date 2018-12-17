import {Route} from '@angular/router';

import {UserRouteAccessService} from 'app/core';
import {PersonalCalendarComponent} from 'app/account/personal-calendar/personal-calendar.component';

export const personalCalendarRoute: Route = {
    path: 'personal-calendar',
    component: PersonalCalendarComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'Personal calendar'
    },
    canActivate: [UserRouteAccessService]
};
