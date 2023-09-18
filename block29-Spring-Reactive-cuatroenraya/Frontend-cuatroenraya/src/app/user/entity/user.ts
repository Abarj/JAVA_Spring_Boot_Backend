export class User {
    
    private username!: string;
    private password: string;

    constructor(username: string, password: string){
        this.username = username;
        this.password = password;
    }

    public setUserName(username: string){
        this.username = username;
    }

    public getUserName(){
        return this.username;
    }

    public setPassword(password: string){
        this.password = password;
    }

    public getPassword(){
        return this.password;
    }
}