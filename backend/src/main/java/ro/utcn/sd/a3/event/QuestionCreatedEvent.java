package ro.utcn.sd.a3.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.a3.dto.QuestionTagDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionCreatedEvent extends BaseEvent {
    private final QuestionTagDTO question;

    public QuestionCreatedEvent(QuestionTagDTO question) {
        super(EventType.QUESTION_CREATED);
        this.question = question;
    }
}
