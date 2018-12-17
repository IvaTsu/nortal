import {Routes} from '@angular/router';
import {EVENT_VIEW_ROUTE} from 'app/event/view/event-view.route';

export const EVENT_ROUTE: Routes = [
    {
        path: 'event',
        children: [EVENT_VIEW_ROUTE]
    }
];
