import questionModel from "../model/questionModel";

class QuestionsListPresenter {
    onCreateQuestion() {
        window.location.assign("#/create-question");
    }

    onInit() {
        questionModel.loadQuestions();
    }
}

const questionsListPresenter = new QuestionsListPresenter();

export default questionsListPresenter;