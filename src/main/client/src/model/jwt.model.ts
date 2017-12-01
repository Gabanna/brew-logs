import { Role } from '../model/user.model';
import _ from 'lodash';

export class JsonWebToken {

    public userName: string;
    private id: string;
    public roles: Array<Role>;

    public isValid(): boolean {
        return !_.isNil(this.userName) && !_.isNil(this.id) && !_.isNil(this.roles);
    }
}