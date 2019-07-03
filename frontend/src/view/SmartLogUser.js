import React, { Component } from "react";
import userModel from "../model/userModel";

import LogUser from "./LogUser";
import logUserPresenter from "../presenter/logUserPresenter";

const mapModelStateToComponentState = modelState => ({
    //users: modelState.users
    username: modelState.loggedUser.id,
    password: modelState.loggedUser.pass

    //questions: modelState.questions
});

export default class SmartLogUser extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(userModel.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        userModel.addListener("change", this.listener);
    }

    componentWillUnmount() {
        userModel.removeListener("change", this.listener);
    }

    render() {
        return (
            <LogUser 
                //onViewDetails={studentsListPresenter.onViewDetails}
                username={this.state.username}
                password={this.state.password} 
                onChange={logUserPresenter.onChange}
                onLogIn={logUserPresenter.onLogIn}
                //questions={this.state.questions} 
                />
        );
    }
}
