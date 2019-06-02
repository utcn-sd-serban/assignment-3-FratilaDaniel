import React from "react";

const QuestionsList = ({ questions, title, onCreateQuestion }) => (
    <div>
        <h2>{ title || "Questions" }</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Author</th>
                    <th>Title</th>
                    <th>Text</th>
                    <th>Date</th>
                    <th>Tags</th>
                </tr>
            </thead>
            <tbody>
                {
                    questions.map((question, index) => (
                        <tr key={index} data-cy="student"> 
                            <td>{question.authorId}</td>
                            <td>{question.title}</td>
                            <td>{question.text}</td>
                            <td>{question.date}</td>
                            <td>{question.tags}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <button onClick={onCreateQuestion} data-cy="add">Add new Question</button>
    </div>
);

export default QuestionsList;