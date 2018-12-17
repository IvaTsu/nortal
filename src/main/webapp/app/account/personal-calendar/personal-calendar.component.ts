import {Component, OnInit} from '@angular/core';
import {Event, EventService} from 'app/core';
import {HttpResponse} from '@angular/common/http';
import {EventAddService} from 'app/event/event-add.service';

@Component({
    selector: 'jhi-personal-calendar',
    templateUrl: './personal-calendar.component.html'
})

export class PersonalCalendarComponent implements OnInit {
    events: Event[];

    constructor(private eventService: EventService, private eventAddService: EventAddService) {
    }

    ngOnInit() {
        this.setEvents();

        this.eventAddService.newEvent.subscribe(aaa => {
            this.setEvents();
        });
    }

    setEvents() {
        this.eventService.query(true).subscribe((res: HttpResponse<Event[]>) => {
            this.events = res.body;
        });
    }
}
