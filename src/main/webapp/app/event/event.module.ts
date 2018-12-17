import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';
import {EventViewComponent} from 'app/event/view/event-view.component';
import {CakeClubSharedLibsModule} from 'app/shared';
import {RouterModule} from '@angular/router';
import {EVENT_ROUTE} from 'app/event/event.route';
import {EventAddModalComponent} from 'app/event/add-modal/event-add-modal.component';

@NgModule({
    imports: [CakeClubSharedLibsModule, RouterModule.forChild(EVENT_ROUTE)],
    declarations: [
        EventAddModalComponent,
        EventViewComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA],
    entryComponents: [EventAddModalComponent],
})

export class CakeClubEventModule {
}
