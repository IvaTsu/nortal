import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

@Component({
    selector: 'jhi-event-view',
    templateUrl: './event-view.component.html'
})

export class EventViewComponent implements OnInit {
    event: Event;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.route.data.subscribe(({event}) => {
            console.log(event);
            this.event = event.body ? event.body : event;
        });
    }

    /* todo: create a method to delete an event (nb! don't forget EventService) */
}
