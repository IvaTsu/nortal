import {Routes} from '@angular/router';

import {activateRoute, passwordRoute, passwordResetFinishRoute, passwordResetInitRoute, registerRoute, settingsRoute} from './';
import {personalCalendarRoute} from 'app/account/personal-calendar/personal-calendar.route';

const ACCOUNT_ROUTES = [activateRoute, passwordRoute, passwordResetFinishRoute, passwordResetInitRoute, registerRoute, settingsRoute, personalCalendarRoute];

export const accountState: Routes = [
    {
        path: '',
        children: ACCOUNT_ROUTES
    }
];
