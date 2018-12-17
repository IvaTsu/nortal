import {Injectable} from '@angular/core';
import {NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';

import {EventAddModalComponent} from 'app/event/add-modal/event-add-modal.component';
import {EventAddService} from 'app/event/event-add.service';

@Injectable({providedIn: 'root'})
export class EventAddModalService {
    private isOpen = false;

    constructor(private modalService: NgbModal, private eventAddService: EventAddService) {
    }

    open(date: Date): NgbModalRef {
        if (this.isOpen) {
            return;
        }
        this.isOpen = true;
        const modalRef = this.modalService.open(EventAddModalComponent);
        (<EventAddModalComponent>modalRef.componentInstance).eventDate = date;
        modalRef.result.then(
            result => {
                this.eventAddService.notify();
                this.isOpen = false;
            },
            reason => {
                this.isOpen = false;
            }
        );
        return modalRef;
    }
}
