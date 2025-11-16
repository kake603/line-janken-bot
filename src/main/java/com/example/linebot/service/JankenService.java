package com.example.linebot.service;

import com.example.linebot.data.JankenLog;
import com.linecorp.bot.webhook.model.ImageMessageContent;
import org.springframework.stereotype.Service;

import com.example.linebot.data.Blob;
import com.example.linebot.data.JankenAPI;
import org.springframework.core.io.Resource;

@Service
public class JankenService {

    private JankenLog jankenLog;
    // データ層：LINEのデータ格納領域にアクセスするクラス
    private Blob blob;

    // データ層：みなさんのAWS EC2のじゃんけんプログラムにアクセスするクラス
    private JankenAPI jankenAPI;

    public JankenService(Blob blob, JankenAPI jankenAPI, JankenLog jankenLog) {
        this.blob = blob;
        this.jankenAPI = jankenAPI;
        this.jankenLog = jankenLog;
    }

    public JankenResult doJanken(ImageMessageContent imc)
            throws Exception {
        // 画像データを取得する
        Resource imageResource = blob.getImageResource(imc);

        // じゃんけんを実行する
        JankenResponse jankenResponse = jankenAPI.playGame(imageResource);

        //じゃんけん永続化
        jankenLog.insert(jankenResponse);

        // ビジネスサービス（じゃんけん）の処理結果を返す
        JankenResult jankenResult
                = new JankenResult(imageResource.contentLength(), jankenResponse);
        return jankenResult;
    }
}