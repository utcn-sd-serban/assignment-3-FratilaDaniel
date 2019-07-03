import userModel from "../model/userModel";
import questionModel from "../model/questionModel"

class LogUserPresenter {

    onLogIn(){
        userModel.userLogIn(); // rest client constructed
        questionModel.resetAuthorization(userModel.client.authorization, userModel.state.loggedUser.id, userModel.state.loggedUser.pass); // set auth for question
        window.location.assign("#/questions");
    }

    onChange(property, value) {
        userModel.changeLoggedUserProperty(property, value);
    }
}

const logUserPresenter = new LogUserPresenter();

export default logUserPresenter;