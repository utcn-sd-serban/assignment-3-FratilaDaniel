import React from "react";

const QuestionsList = ({ questions, title, onCreateQuestion, onFilterByTag, onFilterByTitle, onChangeFilter, filterTag  }) => (
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
                            <td>{question.creationDate}</td>
                            <td>{question.tags}</td>
                        </tr>
                    ))
                }
            </tbody>
        </table>
        <button onClick={onCreateQuestion} data-cy="add">Add new Question</button>
        <br/>
        <label>Filter by keyword</label>
        &emsp;
            <input value={filterTag} 
                onChange={ e => onChangeFilter(e.target.value) } />
            <br />
        <button class="btn btn-primary" onClick={onFilterByTag}>Filter by tag</button>
        <button class="btn btn-primary" onClick={onFilterByTitle}>Filter by title</button>

    </div>
);

export default QuestionsList;