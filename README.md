# AI画像認識 じゃんけんLINE Bot

## 【概要】
大学の授業課題として作成した、AI搭載のLINE Botアプリケーションです。
ユーザーが送信した手の写真を、AWSのAIサービスを用いて解析し、Botとじゃんけん勝負を行います。

**主な機能:**
* **画像判定じゃんけん:** ユーザーの手（グー・チョキ・パー）をAIが判定し、勝敗を返信。
* **戦歴管理:** データベースに対戦成績を記録し、勝率などを計算して表示。
* **テキスト応答:** オウム返し機能や、画像データ量の通知機能。

## 【技術スタック】
* **言語:** Java (Spring Boot)
* **AI/機械学習:** AWS Amazon Rekognition
    * ※LINE Botの用途に特化した画像認識モデルの学習（Custom Labels）も自身で構築・調整を行いました。
* **データベース:** H2 Database
* **インフラ/ツール:** Git, LINE Messaging API

## 【担当・制作範囲】
ベースとなる環境構築は授業の指針に従いつつ、**プレゼンテーション層（コントローラ）からサービス層（ビジネスロジック）の実装**は、要件定義に基づき自身でコーディングを行いました。

## 【工夫した点】

### 1. 役割の分離 (アーキテクチャ)
保守性を高めるため、**Service層（ビジネスロジック）**と**Data層（外部APIやDBとの通信）**を明確に分離しました。
`JankenService` が `Blob` (LINE画像取得), `JankenAPI` (AI判定), `JankenLog` (DB保存) という3つのコンポーネントを統括して処理フローを組み立てる設計としています。

### 2. ユーザー体験 (UX) の向上
レスポンスの遅延によるユーザーの不安を取り除くため、処理フローを工夫しました。
画像を受信した際、AIの判定を待つ間にまず「画像サイズの通知」を即座に返し、その後に「じゃんけん結果」を送ることで、**「Botが正しく反応している」というフィードバックを確実にユーザーへ届ける設計**にしています。

## 【Botの実行について】
現在、バックエンドおよびAIサーバーを停止しており、LINE友達追加による実動作は行えません。
実際の動作の様子は、以下のデモ画像をご参照ください。
<img width="972" height="859" alt="image" src="https://github.com/user-attachments/assets/a4ed4f16-f9e1-439c-9834-9f24c6cd6a5b" />
<img width="974" height="276" alt="image" src="https://github.com/user-attachments/assets/fe27094e-c00a-4702-b249-43356819ad4c" />
<img width="968" height="176" alt="image" src="https://github.com/user-attachments/assets/a75388ba-24bc-41dd-ae45-7ccab68e2dae" />


