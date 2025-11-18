【概要】  
これは、大学の授業で作成したLINE Botアプリケーションです。  
ユーザーが送信した手の写真を外部のAIサービスが判定し、じゃんけんを行います。  
サブ機能として、じゃんけんの戦歴を出力する機能、画像のデータ量を出力する機能、ユーザーが送信した文章をオウム返しする機能があります。  
なお、今回のLINE Botの意図に特化したAIを制作するための機械学習も、自身で行いました。  

【技術スタック】  
使用言語はJavaです。  
機械学習には、AWSが提供しているサービス"Amazon Rekognition"を使用しています。  

【担当した箇所】  
main層は全て、教授の指示を受けながら自身で作成しました。  

【工夫した点】  
1. 役割の分離  
service層（ビジネスロジック）と data層（外部との通信）を明確に分離しました。  
JankenService（ロジック層）が、3つのdata層コンポーネント（Blob, JankenAPI, JankenLog）を呼び出して処理を組み立てる設計にしています。  
2. ユーザー体験 (UX) の向上  
画像が送られてきた際、単に結果を返すだけでなく、「画像サイズの通知」と「じゃんけん結果」の2つのメッセージを同時に返すことで、Botが確実に画像を受け取ったことをユーザーにフィードバックしています。  

【botの実行について】  
現在、このBotはバックエンドおよびAIサーバーを停止しているため、LINEで友達追加しても動作しません。  
動作の様子は、以下の画像をご参照ください。  
<img width="972" height="859" alt="image" src="https://github.com/user-attachments/assets/a4ed4f16-f9e1-439c-9834-9f24c6cd6a5b" />
<img width="974" height="276" alt="image" src="https://github.com/user-attachments/assets/fe27094e-c00a-4702-b249-43356819ad4c" />
<img width="968" height="176" alt="image" src="https://github.com/user-attachments/assets/a75388ba-24bc-41dd-ae45-7ccab68e2dae" />


