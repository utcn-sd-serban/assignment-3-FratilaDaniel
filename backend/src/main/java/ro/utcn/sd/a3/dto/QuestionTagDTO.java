package ro.utcn.sd.a3.dto;


import lombok.Data;
import ro.utcn.sd.a3.entity.Question;


@Data
public class QuestionTagDTO {

    private int id;
    private int authorId;
    private String title;
    private String text;
    private String creationDate;
    private String tags;

    public static QuestionTagDTO ofEntity(Question question, String tags){
        QuestionTagDTO questionTagDTO = new QuestionTagDTO();
        questionTagDTO.setId(question.getId());
        questionTagDTO.setAuthorId(question.getAuthorId());
        questionTagDTO.setTitle(question.getTitle());
        questionTagDTO.setText(question.getText());
        questionTagDTO.setCreationDate(question.getCreationDate().toString());
        questionTagDTO.setTags(tags);
        return questionTagDTO;
    }
}
