import questionModel from "../model/questionModel";

class CreateQuestionPresenter {

    onCreate() {
        questionModel.addQuestion(
            questionModel.state.newQuestion.authorId, 
            questionModel.state.newQuestion.title, 
            questionModel.state.newQuestion.text,
            questionModel.state.newQuestion.tags)
            .then(() => {
                questionModel.changeNewQuestionProperty("authorId", "");
                questionModel.changeNewQuestionProperty("title", "");
                questionModel.changeNewQuestionProperty("text", "");
                questionModel.changeNewQuestionProperty("tags", "");
                window.location.assign("#/questions");
            });
        questionModel.loadQuestions();
    }

    onChange(property, value) {
        questionModel.changeNewQuestionProperty(property, value);
    }

}

const createQuestionPresenter = new CreateQuestionPresenter();

export default createQuestionPresenter;