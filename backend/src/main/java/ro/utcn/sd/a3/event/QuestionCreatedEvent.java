package ro.utcn.sd.a3.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import ro.utcn.sd.a3.dto.QuestionDTO;

@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionCreatedEvent extends BaseEvent {
    private final QuestionDTO question;

    public QuestionCreatedEvent(QuestionDTO question) {
        super(EventType.STUDENT_CREATED);
        this.question = question;
    }
}
