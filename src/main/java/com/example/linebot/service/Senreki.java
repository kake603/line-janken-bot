package com.example.linebot.service;

/**
 * じゃんけんの成績を表すレコード
 *
 * @param totalGames 総試合数
 * @param winCount   勝ち数
 * @param winRate    勝率(パーセンテージ: 0～100)
 */
public record Senreki(
        int totalGames,
        int winCount,
        float winRate
) {}