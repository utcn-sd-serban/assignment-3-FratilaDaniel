package ro.utcn.sd.a3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.a3.entity.Question;
import ro.utcn.sd.a3.entity.QuestionTag;
import ro.utcn.sd.a3.entity.Tag;
import ro.utcn.sd.a3.repository.QuestionTagRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class QuestionTagService {

    private final QuestionTagRepository questionTagRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public void createQuestionTag(Question question, Tag tag){
        QuestionTag questionTag = new QuestionTag(question.getId(), tag.getId());
        questionTagRepository.save(questionTag);
    }

    @Transactional
    public List<QuestionTag> filterQuestionsByTag(Tag tag){
        return questionTagRepository.findAll()
                .stream()
                .filter((QuestionTag questionTag) -> questionTag.getTagId() == tag.getId())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<QuestionTag> getAllQuestionTags(Question question){
        return questionTagRepository.findAll()
                .stream()
                .filter((QuestionTag questionTag) -> questionTag.getQuestionId() == question.getId())
                .collect(Collectors.toList());
    }

}
