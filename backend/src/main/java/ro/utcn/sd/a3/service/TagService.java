package ro.utcn.sd.a3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ro.utcn.sd.a3.dto.TagDTO;
import ro.utcn.sd.a3.entity.Tag;
import ro.utcn.sd.a3.event.TagCreatedEvent;
import ro.utcn.sd.a3.repository.TagRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public TagDTO create(TagDTO dto) {
        Tag tag= new Tag();
        tag.setId(dto.getId());
        tag.setDescription(dto.getDescription());

        TagDTO output = TagDTO.ofEntity(tagRepository.save(tag));
        eventPublisher.publishEvent(new TagCreatedEvent(output));
        return output;
    }

}
