import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { CalendarModule } from 'angular-calendar';

import {CakeClubSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent} from './';
import {EventCalendarModule} from './calendar/event-calendar.module';

@NgModule({
    imports: [CakeClubSharedLibsModule, CommonModule, FormsModule, CalendarModule, EventCalendarModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [CakeClubSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent, EventCalendarModule]
})
export class CakeClubSharedCommonModule {}
