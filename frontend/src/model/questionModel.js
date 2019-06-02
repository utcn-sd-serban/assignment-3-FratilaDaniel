import { EventEmitter } from "events";
import RestClient from "../rest/RestClient";
import WebSocketListener from "../ws/WebSocketListener";

const client = new RestClient("serban", "password");
const listener = new WebSocketListener("serban", "password");

class QuestionModel extends EventEmitter {
    constructor() {
        super();
        this.state = {
            questions: [],
            newQuestion: {
                authorId: "",
                title: "",
                text: "",
                date: "",
                tags: []
            }
        };
    }

    loadQuestions() {
        return client.loadAllQuestions().then(questions => {
            this.state = { 
                ...this.state, 
                questions: questions 
            };
            this.emit("change", this.state);
        })
    }

    addQuestion(author, title, text, date, tags){
        return client.createQuestion(author, title, text, date, tags)
        .then(question => this.appendQuestion(question));
    }

    appendQuestion(question){
        this.state = { 
            ...this.state, 
            questions: this.state.questions.concat([question]) 
        };
        this.emit("change", this.state);

    }

    changeNewQuestionProperty(property, value) {
        this.state = {
            ...this.state,
            newQuestion: {
                ...this.state.newQuestion,
                [property]: value
            }
        };
        this.emit("change", this.state);
    }
}

const questionModel = new QuestionModel();

listener.on("event", event => {
    if (event.type === "QUESTION_CREATED") {
        questionModel.appendQuestion(event.question);
    }
});

export default questionModel;