import {Component, OnInit} from '@angular/core';
import {NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {JhiEventManager} from 'ng-jhipster';
import {HttpResponse} from '@angular/common/http';

import {LoginModalService, Principal, Account} from 'app/core';
import {Event, EventService} from 'app/core';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;
    events: Event[];

    constructor(private principal: Principal, private loginModalService: LoginModalService, private eventManager: JhiEventManager,
                private eventService: EventService) {
    }

    ngOnInit() {
        this.principal.identity().then(account => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();

        this.eventService.query(false).subscribe((res: HttpResponse<Event[]>) => {
            for (const event of res.body) {
                event.start = new Date(event.start);
            }
            this.events = res.body;
        });
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', message => {
            this.principal.identity().then(account => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }

    /* todo: create a method to query events with additional params (number of participants) */
}
