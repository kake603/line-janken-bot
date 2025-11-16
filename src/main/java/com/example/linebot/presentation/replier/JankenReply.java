package com.example.linebot.presentation.replier;

import com.example.linebot.service.JankenResult;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.TextMessage;

public class JankenReply {

    public static final String MESSAGE = "じゃんけんの結果です（画像サイズ：%d バイト）";

    private final JankenResult result;

    public JankenReply(JankenResult result) {
        this.result = result;
    }

    public Message reply() {
        long imageSize = result.imageSize();

        String responseText = result.response().toString();

        String fullMessage = String.format(MESSAGE, imageSize) + "\n" + responseText;

        return new TextMessage(fullMessage);
    }
}