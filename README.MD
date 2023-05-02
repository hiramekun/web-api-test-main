# API コーディングテストについて

## はじめに

SpringBoot または Ktor で REST API の実装をお願いします。   
Kotlin の使用を推奨しますが、Java で実装していただいても問題ありません。  
所要時間は2時間が目安で、最大で4時間になります。   

制限時間内に全ての API を作り切ることは難しいと思いますので、  
参照系の API と登録系の API を最低1つずつ作成してください。  
また、全てのタスクを行う必要もありません。どのタスクを選んでなぜそうしたのかは後ほどご回答ください。  
実施していただきたいタスクは以下の通りです。

## 提出方法
ご自身の github アカウントに repository を作成し、Pull request の作成をお願いいたします。  
※ こちらの repository には Pull request を作成しないようにお気をつけください

## タスク一覧

1. REST API の作成（必須で参照系、登録系の API を1つずつ）
2. テストコードの作成（必須）
3. openAPI 形式で API 定義の作成（任意）
4. ER図の作成（任意）
5. クラス図の作成（任意）

## インストール済みのライブラリ

- SpringBoot
- SpringBoot starter test
- SpringBoot Exposed
- Spring JDBC
- Spring Web
- Ktor
- Koin
- Exposed
    - Exposed のテーブル定義クラスを事前に作成済みです（UserTable.kt, PaymentTable.kt）
    - また、Exposed の実装サンプルを UserRepository.kt に記載しておりますので、ご参照ください。
    - Exposed 以外のライブラリを使っていただいても問題ありません。
- Flyway
- 上記以外に必要なライブラリは自由に追加していただいて構いません

## データベースとテーブル

- MySQL
- 接続情報は docker/docker-compose.yml をご参照ください
- `./gradlew flyway` コマンドでデータベースのマイグレションを実施します
- テーブル定義、関連については `database/migration` ディレクトリのマイグレーションファイルをご参照ください

## 作っていただきたい API

1. user_id を指定して users テーブルから取得する API（参照系）
2. user の情報を送信して users テーブルに登録する API（登録系）
3. users テーブルの情報を指定した件数分、かつ、ページングを考慮して取得する API（参照系）
4. payments テーブルを期間指定で取得し、amount の合計を取得する API（参照系）
5. payments テーブルにデータを登録する api を以下の条件を満たした上で作成してください（登録系）
    1. fee_rate は 4%、tax_rate は10% で計算し、billing_amount に計算した金額を設定する
        1. 例) amount が10,000の場合 billing_amount は 10,440 になる
    2. status は 0 で登録する
    3. payments 登録時に payment_bank_accounts も同時にデータを登録する

## その他

- controller, service, repository でパッケージを分けていますが、パッケージの構成は自由です
  - パッケージの構成は自由に追加、変更していただいて問題ありません
