package ro.utcn.sd.a3.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.a3.dto.TagDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class TagCreatedEvent extends BaseEvent {
    private final TagDTO tag;

    public TagCreatedEvent(TagDTO tag) {
        super(EventType.TAG_CREATED);
        this.tag = tag;
    }
}
