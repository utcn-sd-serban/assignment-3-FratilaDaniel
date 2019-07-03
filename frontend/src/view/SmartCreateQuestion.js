import React, { Component } from "react";
import questionModel from "../model/questionModel";

import CreateQuestion from "./CreateQuestion";
import createQuestionPresenter from "../presenter/createQuestionPresenter";

const mapModelStateToComponentState = modelState => ({
    authorId: modelState.newQuestion.authorId,
    title: modelState.newQuestion.title,
    text: modelState.newQuestion.text,
    date: modelState.newQuestion.date,
    tags: modelState.newQuestion.tags
});

export default class SmartCreateQuestion extends Component {
    constructor() {
        super();
        this.state = mapModelStateToComponentState(questionModel.state);
        this.listener = modelState => this.setState(mapModelStateToComponentState(modelState));
        questionModel.addListener("change", this.listener);
    }

    componentWillUnmount() {
        questionModel.removeListener("change", this.listener);
    }

    render() {
        return (
            <CreateQuestion 
                onCreate={createQuestionPresenter.onCreate}
                onChange={createQuestionPresenter.onChange}
                authorId={this.state.authorId}
                title={this.state.title}
                text={this.state.text}
                tags={this.state.tags} />
        );
    }
}
