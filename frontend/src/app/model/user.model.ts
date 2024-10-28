export class User {
    id: number;
    username: string;
    email: string;
    encodedPassword: string;
    roles: string[];

    constructor() {
        this.id = 0;
        this.username = "";
        this.email = "";
        this.encodedPassword = "";
        this.roles = [];
    }
}