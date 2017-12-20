import moment from 'moment';

export class BrewLog {
    
    private id: number;
    private name: string;
    private subject: string;
    private created: number;
    private updated: number;

    constructor(){
        moment.locale('de');
    }

    public getId() : number {
        return this.id;
    }

    public getName() : string {
        return this.name;
    }

    public getSubject() : string {
        return this.subject;
    }

    public getCreated(format?: string): moment.Moment | string {
        let value = moment(this.created);
        return format ? value.format(format) : value;
    }

    public getUpdated(format?: string): moment.Moment | string {
        let value = moment(this.updated);
        return format ? value.format(format) : value;
    }
}