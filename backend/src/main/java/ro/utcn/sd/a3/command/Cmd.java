package ro.utcn.sd.a3.command;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.utcn.sd.a3.dto.QuestionTagDTO;
import ro.utcn.sd.a3.service.QuestionService;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Cmd {

    private final QuestionService questionService;


    public List execute(String command, Object args){
        // merge cu case?
        if(command.equals("viewQuestions")){
            return questionService.listAll();
        }
        else if (command.equals("addQuestion")){
            QuestionTagDTO questionTagDTO = ((QuestionTagDTO)args);
            //return new ArrayList<QuestionTagDTO>().add(questionService.create(questionTagDTO));
            List<QuestionTagDTO> l = new ArrayList<QuestionTagDTO>();
            l.add(questionService.create(questionTagDTO));
            return l;
        }
        else {
            return new ArrayList<>();
        }
    }

}
