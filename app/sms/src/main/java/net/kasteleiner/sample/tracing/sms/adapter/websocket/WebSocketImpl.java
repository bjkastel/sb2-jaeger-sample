package net.kasteleiner.sample.tracing.sms.adapter.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.kasteleiner.sample.tracing.sms.domain.SmsDto;
import net.kasteleiner.sample.tracing.sms.domain.WebSocket;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketImpl implements WebSocket {
    private final ObjectMapper mapper;
    private final SimpMessagingTemplate messageTemplate;

    public void sendWebSocketUpdate(SmsDto sms) {
        try {
            this.messageTemplate.convertAndSend("/sms", mapper.writeValueAsString(sms));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("SMS parsing issue", e);
        }
    }

}
