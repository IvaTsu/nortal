import { IUser } from 'app/core/user/user.model';

export interface IUserSkills {
    id?: number;
    strength?: number;
    speed?: number;
    stamina?: number;
    cooking?: number;
    user?: IUser;
}

export class UserSkills implements IUserSkills {
    constructor(
        public id?: number,
        public strength?: number,
        public speed?: number,
        public stamina?: number,
        public cooking?: number,
        public user?: IUser
    ) {}
}
