import questionModel from "../model/questionModel";

class QuestionsListPresenter {
    onCreateQuestion() {
        window.location.assign("#/create-question");
    }

    onInit() {
        questionModel.loadQuestions();
    }

    onFilterByTag(){
        questionModel.filterByTag();
        window.location.assign("#/filtered-questions");
    }

    onFilterByTitle(){
        questionModel.filterByTitle();
        window.location.assign("#/filtered-questions");
    }

    onChangeFilter(value){
        questionModel.clearFilters();
        questionModel.changeFilterProperty(value);
    }


}

const questionsListPresenter = new QuestionsListPresenter();

export default questionsListPresenter;