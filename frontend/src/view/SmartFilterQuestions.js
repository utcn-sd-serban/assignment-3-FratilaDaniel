import React, { Component } from "react";
import questionModel from "../model/questionModel"
import FilterQuestions from "./FilterQuestions"

const mapModelStateToComponentState = modelState => ({
    filterTag: modelState.filterTag,
    filteredQuestions: modelState.filteredQuestions
});

export default class SmartFilterQuestions extends Component {
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
            <FilterQuestions 
                filterTag = {this.state.filterTag}
                questions={this.state.filteredQuestions}
                />
        );
    }
}