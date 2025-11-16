package com.example.linebot.service;

import com.example.linebot.data.JankenLog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SenrekiService {

    private JankenLog jankenLog;

    public SenrekiService(JankenLog jankenLog) {
        this.jankenLog = jankenLog;
    }

    public Senreki calcSenreki() {
        List<JankenResponse> jankenResponses = jankenLog.selectAll();

        // (1) 総試合数
        int totalGames = jankenResponses.size();

        // (2) 勝利数
        int winCount = 0;
        for (JankenResponse res : jankenResponses) {
            if ("勝ち".equals(res.kekka())) {
                winCount++;
            }
        }

        // (3) 勝率をパーセンテージ（0～100）で計算
        //     ゲーム数が0なら0%
        float winRate = totalGames > 0
                ? (float) winCount / totalGames * 100f
                : 0f;

        // (4) 結果を返却（winRate: %表記）
        return new Senreki(totalGames, winCount, winRate);
    }
}