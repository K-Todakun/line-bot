package com.example.linebot;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.PostbackAction;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.template.ConfirmTemplate;
import com.linecorp.bot.model.response.BotApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;


@RestController
public class Push {


    @GetMapping("test")
    public String hello(HttpServletRequest request) {
        return "Get from " + request.getRequestURL();
    }

    private String userId = "Uad5eca0a6884415b935961c456937008";
    private static final Logger log = LoggerFactory.getLogger(Push.class);
    public Push(LineMessagingClient client) {
        this.client = client;
    }
    private final LineMessagingClient client;

    // 確認メッセージをpush
    @GetMapping("confirm")
    public String pushConfirm() {
        String text = "質問だよ";
        try {
            Message msg = new TemplateMessage(text,
                    new ConfirmTemplate("いいかんじ？",
                            new PostbackAction("おけまる", "CY"),
                            new PostbackAction("やばたん", "CN")));
            PushMessage pMsg = new PushMessage(userId, msg);
            BotApiResponse resp = client.pushMessage(pMsg).get();
            log.info("Sent messages: {}", resp);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        return text;
    }

}
