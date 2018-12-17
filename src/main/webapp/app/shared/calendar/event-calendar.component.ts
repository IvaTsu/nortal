import {Component, Input, OnChanges, ChangeDetectionStrategy} from '@angular/core';
import {Router} from '@angular/router';
import {CalendarEvent, CalendarEventAction} from 'angular-calendar';
import {isSameDay, isSameMonth, parse} from 'date-fns';
import {Subject} from 'rxjs';
import {Event} from 'app/core';
import {EventAddModalService} from 'app/event/add-modal/event-add-modal-service';

const colors: any = {
    red: {
        primary: '#ad2121',
        secondary: '#FAE3E3'
    },
    blue: {
        primary: '#1e90ff',
        secondary: '#D1E8FF'
    },
    yellow: {
        primary: '#e3bc08',
        secondary: '#FDF1BA'
    }
};

@Component({
    selector: 'jhi-event-calendar',
    templateUrl: './event-calendar.component.html',
    changeDetection: ChangeDetectionStrategy.OnPush,
    styleUrls: ['event-calendar.css']
})
export class EventCalendarComponent implements OnChanges {

    // https://www.npmjs.com/package/angular-calendar#getting-started

    @Input()
    showAddEvent: boolean = false;

    @Input()
    events: Event[] = [];

    @Input()
    addSingleUserEvent: boolean = false;

    viewDate: Date = new Date();
    refresh: Subject<any> = new Subject();
    activeDayIsOpen: boolean = false;
    calendarEvents: CalendarEvent[] = [];

    actions: CalendarEventAction[] = [
        {
            // TODO EI TOOTA
            label: "<span><fa-icon [icon]=\"'pencil-alt'\"></fa-icon></span>",
            onClick: ({event}: { event: CalendarEvent }): void => {
                this.handleEvent('Edited', event);
            }
        },
        {
            label: "<span><fa-icon [icon]=\"'times'\"></fa-icon></span>",
            onClick: ({event}: { event: CalendarEvent }): void => {
                this.events = this.events.filter(iEvent => iEvent !== event);
                this.handleEvent('Deleted', event);
            }
        }
    ];

    constructor(private router: Router, private eventAddModalService: EventAddModalService) {
    }

    ngOnChanges() {
        this.setCalendarEvents();
    }

    dayClicked({date, events}: { date: Date; events: CalendarEvent[] }): void {
        if ((isSameDay(this.viewDate, date) && events.length > 0)) {
            this.activeDayIsOpen = !this.activeDayIsOpen;
        }
        if (this.addSingleUserEvent && events.length === 0) {
            this.eventAddModalService.open(date);
        }
    }

    setCalendarEvents(): void {
        this.calendarEvents = [];
        if (!this.events) {
            return;
        }
        this.events.forEach(event => {
            this.calendarEvents.push({
                id: event.id,
                start: parse(event.start),
                actions: this.actions,
                title: event.title,
                color: colors.red,
                meta: {
                    description: event.description,
                    users: event.users
                }
            });
        });
    }

    handleEvent(action: string, event: CalendarEvent): void {
        console.log('clicked' + action);
        this.router.navigate(['/event', event.id]);
    }
}
