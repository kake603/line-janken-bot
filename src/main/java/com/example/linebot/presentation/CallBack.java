package com.example.linebot.presentation;

import com.example.linebot.presentation.replier.*;
import com.example.linebot.service.JankenResult;
import com.example.linebot.service.SenrekiService;
import com.linecorp.bot.messaging.model.Message;
import com.linecorp.bot.messaging.model.TextMessage;
import com.linecorp.bot.spring.boot.handler.annotation.EventMapping;
import com.linecorp.bot.spring.boot.handler.annotation.LineMessageHandler;
import com.linecorp.bot.webhook.model.FollowEvent;

//*-- 以下のimport を追加 --*//
import com.example.linebot.presentation.replier.Parrot;
import com.linecorp.bot.webhook.model.MessageContent;
import com.linecorp.bot.webhook.model.MessageEvent;
import com.linecorp.bot.webhook.model.TextMessageContent;
import java.util.List;

import com.linecorp.bot.webhook.model.ImageMessageContent;
import com.example.linebot.service.JankenService;


@LineMessageHandler
public class CallBack {

    /*-- （中略） --*/
    private JankenService jankenService;
    private SenrekiService senrekiService;

    public CallBack(JankenService jankenService, SenrekiService senrekiService) {
        this.jankenService = jankenService;
        this.senrekiService = senrekiService;
    }
    // 文章で話しかけられたとき（テキストメッセージのイベント）に対応する
    @EventMapping
    public List<Message> handleMessage(MessageEvent event) throws Exception {
        MessageContent mc = event.message();

// 以前作ったクラスの handleMessage メソッドの switch 文を書き換える
        switch (mc) {
            case TextMessageContent tmc:
                // MessageContent が文字列のメッセージ (TextMessageContent) だったとき、
                // tmc = mc にする
                return handleText(tmc);
            case ImageMessageContent imc:
                // MessageContent が画像のメッセージ (ImageMessageContent) だったとき、
                // imc = mc にする
                return handleJanken(imc);
            default:
                // それ以外のメッセージだったとき
        }        throw new RuntimeException("対応していないメッセージ");

    }

    public List<Message> handleText(TextMessageContent tmc) {
        String text = tmc.text();
        switch (text) {
            case "戦歴":
                // 戦歴を取得して返信
                return List.of(
                        new SenrekiReply(senrekiService.calcSenreki())
                                .reply()
                );


            // 何もせずに default に進む
            default:
                // おうむ返しのメッセージを作る
                Parrot parrot = new Parrot(text);
                return List.of(parrot.reply());
        }

    }

    // 以前作ったクラスに追加する
    public List<Message> handleJanken(ImageMessageContent imc)
            throws Exception {
        // じゃんけんを実施するビジネスサービスを呼び出す
        JankenResult jankenResult = jankenService.doJanken(imc);
        return List.of(
                new ImageSizeReply(jankenResult).reply(),
                new JankenReply(jankenResult).reply()
        );
    }
}