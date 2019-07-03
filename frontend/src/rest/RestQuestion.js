const BASE_URL = "http://localhost:8080";

export default class RestQuestion
{

    // constructor(){

    // }

    setAuth(authorization){
        this.authorization = authorization;
    }


    loadAllQuestions()
    {
        return fetch(BASE_URL + "/questions", {
            method: "GET",
            headers: {
                "Authorization": this.authorization
            }
        }).then(response => {
            return response.json()});
    }


    createQuestion(author, title, text, tags)
    {
        return fetch(BASE_URL + "/questions", {

            method: "POST",
            body: JSON.stringify({
                author: author,
                title: title,
                text: text,
                creationDate: new Date().getTime(),
                tags: tags,
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type" : "application/json"
            }
        }).then(response =>{
            return response.json()
        });
    }
}