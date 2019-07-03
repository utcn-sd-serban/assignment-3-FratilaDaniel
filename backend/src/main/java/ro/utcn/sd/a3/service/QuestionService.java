package ro.utcn.sd.a3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ro.utcn.sd.a3.dto.QuestionTagDTO;
import ro.utcn.sd.a3.entity.Question;
import ro.utcn.sd.a3.entity.QuestionTag;
import ro.utcn.sd.a3.entity.Tag;
import ro.utcn.sd.a3.event.QuestionCreatedEvent;
import ro.utcn.sd.a3.repository.QuestionRepository;
import ro.utcn.sd.a3.repository.QuestionTagRepository;
import ro.utcn.sd.a3.repository.TagRepository;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionTagRepository questionTagRepository;
    private final TagRepository tagRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public List<QuestionTagDTO> listAll() {
        List<QuestionTagDTO> questionTagDTO = new ArrayList<QuestionTagDTO>();

        for(Question question : questionRepository.findAll()){
            String tags = "";
            for(QuestionTag questionTag : questionTagRepository.findAll()){
                if(question.getId() == questionTag.getQuestionId()){
                    tags += (tagRepository.findById(questionTag.getTagId()).get().toString() + ", ");
                }
            }
            questionTagDTO.add(QuestionTagDTO.ofEntity(question, tags));
        }
        questionTagDTO.sort(new Comparator<QuestionTagDTO>() {
            @Override
            public int compare(QuestionTagDTO o1, QuestionTagDTO o2) {
                Date d1 = new Date();
                Date d2 = new Date();
                try {
                    d1 = new SimpleDateFormat("yyyy-mm-dd").parse(o1.getCreationDate());
                    d2 = new SimpleDateFormat("yyyy-mm-dd").parse(o2.getCreationDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(d1.getTime() > d2.getTime())
                    return -1;
                else return 1;
            }
        });
        return questionTagDTO;
    }

    @Transactional
    public QuestionTagDTO create(QuestionTagDTO dto) {
        Question question = new Question();
        question.setAuthorId(dto.getAuthorId());
        question.setTitle(dto.getTitle());
        question.setText(dto.getText());
        //Date date = new Date(dto.getCreationDate());
        question.setCreationDate(new Date());
        questionRepository.save(question);

        for(String tag: dto.getTags().split(",")){
            if(tagRepository.findByDescription(tag) == null){
                tagRepository.save(new Tag(tag));
            }
            questionTagRepository.save(new QuestionTag(question.getId(), tagRepository.findByDescription(tag).getId()));
        }

        System.out.println("TAGS:");
        for(Tag t: tagRepository.findAll()){
            System.out.println(t.getId() + ":" + t.getDescription());
        }

        System.out.println("QUESTION TAGS:");
        for(QuestionTag q: questionTagRepository.findAll()){
            System.out.println(q.getId() + ":" + q.getQuestionId() + "," + q.getTagId());

        }


        QuestionTagDTO output = QuestionTagDTO.ofEntity(question, dto.getTags());
        eventPublisher.publishEvent(new QuestionCreatedEvent(output));
        return output;
    }

}
