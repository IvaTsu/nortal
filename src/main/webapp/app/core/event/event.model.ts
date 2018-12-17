export interface IEvent {
    id?: any;
    title?: string;
    description?: string;
    start?: Date;
    eventColor?: string;
    minDate?: Date;
    maxDate?: Date;
    users?: any[];
}

export class Event implements IEvent {
    constructor(
        public id?: any,
        public title?: string,
        public description?: string,
        public start?: Date,
        public eventColor?: string,
        public minDate?: Date,
        public maxDate?: Date,
        public users?: any[]
    ) {
        this.id = id ? id : null;
        this.title = title ? title : null;
        this.description = description ? description : null;
        this.start = start ? start : null;
        this.eventColor = eventColor ? eventColor : null;
        this.minDate = minDate ? minDate : null;
        this.maxDate = maxDate ? maxDate : null;
        this.users = users ? users : null;
    }
}
