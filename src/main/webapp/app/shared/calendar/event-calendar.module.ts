import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import {EventCalendarComponent} from 'app/shared/calendar/event-calendar.component';
import {CalendarHeaderComponent} from "app/shared/calendar/header/calendar-header.component";

@NgModule({
    imports: [
        CommonModule,
        CalendarModule.forRoot({
            provide: DateAdapter,
            useFactory: adapterFactory
        })
    ],
    declarations: [EventCalendarComponent, CalendarHeaderComponent],
    exports: [EventCalendarComponent, CalendarHeaderComponent]
})
export class EventCalendarModule {}
