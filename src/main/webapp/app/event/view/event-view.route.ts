import {Injectable} from '@angular/core';
import {Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Route} from '@angular/router';
import {EventViewComponent} from 'app/event';
import {EventService, Event} from 'app/core';

@Injectable({providedIn: 'root'})
export class EventResolve implements Resolve<any> {
    constructor(private service: EventService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            console.log(id);
            return this.service.find(id);
        }
        return new Event();
    }
}

export const EVENT_VIEW_ROUTE: Route = {
    path: ':id',
    component: EventViewComponent,
    resolve: {
        event: EventResolve
    },
    data: {
        pageTitle: 'Event'
    }
};
