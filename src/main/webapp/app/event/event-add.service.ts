import {EventEmitter, Injectable, Output} from '@angular/core';

@Injectable()
export class EventAddService {

    @Output() newEvent: EventEmitter<boolean> = new EventEmitter();

    notify() {
        this.newEvent.emit(true);
    }
}
