package com.example.linebot.presentation.replier;

import com.example.linebot.service.Senreki;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.TextMessage;

public class SenrekiReply {

    public static final String MESSAGE =
            "🎌 戦歴 🎌\n" +
                    "総試合数: %d\n" +
                    "勝ち数  : %d\n" +
                    "勝率    : %.2f%%";

    private final Senreki senreki;

    public SenrekiReply(Senreki senreki) {
        this.senreki = senreki;
    }

    public Message reply() {
        String body = String.format(
                MESSAGE,
                senreki.totalGames(),
                senreki.winCount(),
                senreki.winRate()
        );
        return new TextMessage(body);
    }
}