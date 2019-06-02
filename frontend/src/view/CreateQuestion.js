import React from "react";

const CreateQuestion = ({ authorId, title, text, date, tags, onCreate, onChange }) => (
    <div>
        <h2>Add Question</h2>
        <div>
            <label>AuthorId: </label>
            <input value={authorId} data-cy="firstName"
                onChange={ e => onChange("authorId", e.target.value) } />
            <br />
            <label>Title: </label>
            <input value={title} 
                onChange={ e => onChange("title", e.target.value) } />
            <br />
            <label>Text: </label>
            <input value={text} 
                onChange={ e => onChange("text", e.target.value) } />
            <br />
            <label>Date: </label>
            <input value={date} 
                onChange={ e => onChange("date", e.target.value) } />
            <br />
            <label>Tags: </label>
            <input value={tags} 
                onChange={ e => onChange("tags", e.target.value) } />
            <br />

            <button onClick={onCreate} data-cy="create">Create!</button>
        </div>
    </div>
);

export default CreateQuestion;