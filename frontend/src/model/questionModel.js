import { EventEmitter } from "events";
import RestQuestion from "../rest/RestQuestion";
import RestClient from "../rest/RestClient";
import WebSocketListener from "../ws/WebSocketListener";


        
class QuestionModel extends EventEmitter {
    constructor() {
        super();
        this.restQuestion = new RestQuestion();
        this.state = {
            questions: [],
            newQuestion: {
                authorId: "",
                title: "",
                text: "",
                creationDate: "",
                tags: ""
            },
            filterTag : "",
            filteredQuestions: []
        };
        //this.restQuestion.setAuth(client.authorization);
    }

    resetAuthorization(authorization, id, pass){
        this.restQuestion.setAuth(authorization);
        const listener = new WebSocketListener(id, pass);
        
        listener.on("event", event => {
            if (event.type === "QUESTION_CREATED") {
                questionModel.appendQuestion(event.question);
            }
        });
        //listener = new WebSocketListener(id, pass);
        this.emit("change", this.state);
    }


    loadQuestions() {
        return this.restQuestion.loadAllQuestions().then(questions => {
            this.state = { 
                ...this.state, 
                questions: questions 
            };
            this.emit("change", this.state);
        })
    }

    addQuestion(author, title, text, tags){
        return this.restQuestion.createQuestion(author, title, text, tags)
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
    // basic pana aici, list si create



    addFilteredQuestion(foundQuestion){
        this.state = {
            ...this.state,
            filteredQuestions: this.state.filteredQuestions.concat([foundQuestion])
        };
        this.emit("change", this.state);
    }

    changeFilterProperty(value){
        this.state = {
            ...this.state,
            filterTag: value
        };
        this.emit("change", this.state);
    }

    clearFilters(){
        this.state = {
            ...this.state,
            filterTag: "",
            filteredQuestions: []
        }
        this.emit("change", this.state);
    }


    filterByTag(){
        for(var iterator = 0; iterator < this.state.questions.length; ++iterator){
            if(this.state.questions[iterator].tags === this.state.filterTag){
                this.addFilteredQuestion(this.state.questions[iterator]);
            }
        }
        this.emit("change", this.state);
    }


    filterByTitle(){
        for(var iterator = 0; iterator < this.state.questions.length; ++iterator){
            if(this.state.questions[iterator].title === this.state.filterTag){
                this.addFilteredQuestion(this.state.questions[iterator]);
            }
        }
        this.emit("change", this.state);
    }

    // gata partea cu filter


}


const questionModel = new QuestionModel();


export default questionModel;