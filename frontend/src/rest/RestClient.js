const BASE_URL = "http://localhost:8080";

export default class RestClient {
    constructor(username, password) {
        this.authorization = "Basic " + btoa(username + ":" + password);
    }

    userLogin(id, username, password)
    {
        return fetch(BASE_URL + "/login", {

            method: "POST",
            body: JSON.stringify({
                id: id,
                username: username,
                pass: password
            }),
            headers: {
                "Authorization": this.authorization,
                "Content-Type" : "application/json"
            }
        }).then(response => {
            return response;
        });
    }
}