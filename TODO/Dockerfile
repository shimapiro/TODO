# ベースとなる公式のOpenJDKイメージを使用
FROM openjdk:17-jdk-slim

# 作業ディレクトリを設定
WORKDIR /app

# アプリケーションのJARファイルをコンテナにコピー
COPY target/TODO-0.0.1-SNAPSHOT.jar app.jar

# コンテナ起動時に実行されるコマンド
CMD ["java", "-jar", "app.jar"]

# コンテナがリッスンするポートを指定
EXPOSE 8080
