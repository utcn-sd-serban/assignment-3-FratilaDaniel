package ro.utcn.sd.a3.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.sd.a3.command.Cmd;
import ro.utcn.sd.a3.dto.QuestionTagDTO;
import ro.utcn.sd.a3.event.BaseEvent;
import ro.utcn.sd.a3.service.QuestionService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final Cmd cmd;
    private final SimpMessagingTemplate messagingTemplate;

    @GetMapping("/questions")
    public List<QuestionTagDTO> readAll() {
        return cmd.execute( "viewQuestions", null);
    }

    @PostMapping("/questions")
    public QuestionTagDTO create(@RequestBody QuestionTagDTO dto) {
        return (QuestionTagDTO)cmd.execute("addQuestion", dto).get(0);
    }

    @EventListener(BaseEvent.class)
    public void handleEvent(BaseEvent event) {
        log.info("Got an event: {}.", event);
        messagingTemplate.convertAndSend("/topic/events", event);
    }

}
