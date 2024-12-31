export class User {
    id: number;
    username: string;
    email: string;
    phoneNumber: string;
    encodedPassword: string;
    roles: string[];

    constructor() {
        this.id = 0;
        this.username = "";
        this.email = "";
        this.phoneNumber = ""
        this.encodedPassword = "";
        this.roles = [];
    }
}