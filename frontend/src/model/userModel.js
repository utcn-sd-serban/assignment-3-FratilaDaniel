import { EventEmitter } from "events";
import RestClient from  "../rest/RestClient";

class UserModel extends EventEmitter {
    constructor() {
        super();
        this.client = new RestClient();
        this.state = {
            loggedUser: {
                id: "",
                pass: ""    
            }
        };
    }

    changeLoggedUserProperty(property, value) {
        this.state = {
            ...this.state,
            loggedUser: {
                ...this.state.loggedUser,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }


    userLogIn(){
        this.client = new RestClient(this.state.loggedUser.id, this.state.loggedUser.pass);
        //this.listener = new WebSocketListener(this.state.loggedUser.id, this.state.loggedUser.pass);
        this.emit("change", this.state);
    }


}

const userModel = new UserModel();

export default userModel;