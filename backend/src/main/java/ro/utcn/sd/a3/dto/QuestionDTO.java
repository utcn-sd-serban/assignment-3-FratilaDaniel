package ro.utcn.sd.a3.dto;


import lombok.Data;
import ro.utcn.sd.a3.entity.Question;


@Data
public class QuestionDTO {


    private int id;
    private int authorId;
    private String title;
    private String text;
    //private List<String> tags;

    public static QuestionDTO ofEntity(Question question){
        QuestionDTO questionDTO = new QuestionDTO();
        questionDTO.setId(question.getId());
        questionDTO.setAuthorId(question.getAuthorId());
        questionDTO.setTitle(question.getTitle());
        questionDTO.setText(question.getText());
        return questionDTO;
    }




//    private String firstName;
//    private String lastName;
//    private List<Integer> grades;
//
//    public static StudentDTO ofEntity(Student student) {
//        StudentDTO studentDTO = new StudentDTO();
//        studentDTO.setFirstName(student.getFirstName());
//        studentDTO.setLastName(student.getLastName());
//        if (!CollectionUtils.isEmpty(student.getGrades())) {
//            studentDTO.setGrades(student.getGrades().stream()
//                    .map(Grade::getScore)
//                    .collect(Collectors.toList()));
//        }
//        return studentDTO;
//    }

}
