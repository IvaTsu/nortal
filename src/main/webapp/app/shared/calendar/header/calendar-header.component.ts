import { Component, Input, Output, EventEmitter } from '@angular/core';
import {Router} from "@angular/router";

@Component({
    selector: 'jhi-calendar-header',
    templateUrl: 'calendar-header.component.html',
    styleUrls: ['calendar-header.css']
})
export class CalendarHeaderComponent {

    constructor(private router: Router) {}

    @Input()
        viewDate: Date;

    @Input()
        locale: string = 'en';

    @Input()
        showAddEvent: boolean = false;

    @Output()
        viewDateChange: EventEmitter<Date> = new EventEmitter();

    addEvent() {
        alert('todo: create event form');

        /* todo: create a new component (modal or view) where user can insert group events (nb! for http-post-request use saveMultiple() in EventService) */

    }
}
