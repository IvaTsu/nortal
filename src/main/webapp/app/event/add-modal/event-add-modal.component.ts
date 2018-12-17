import {AfterViewInit, Component, ElementRef, Input, OnInit, Output, Renderer, EventEmitter} from '@angular/core';
import {Event, EventService} from 'app/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'jhi-event-add-modal',
    templateUrl: './event-add-modal.component.html',
    styleUrls: ['event-add-modal.css']
})

export class EventAddModalComponent implements OnInit, AfterViewInit {

    event: Event;

    @Input()
    eventDate: Date;

    @Output()
    newEvent = new EventEmitter<Event>();

    constructor(private eventService: EventService, private renderer: Renderer, public activeModal: NgbActiveModal, private elementRef: ElementRef) {
    }

    ngOnInit() {
        this.event = {
            start: this.eventDate
        };
    }

    ngAfterViewInit() {
        setTimeout(() => this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#eventTitle'), 'focus', []), 0);
    }

    cancel() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.eventService.save(this.event).subscribe(
            () => {
                this.activeModal.close('save');
                this.newEvent.emit(this.event);
            }
        );
    }
}
