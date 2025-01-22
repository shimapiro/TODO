# ベースとなる公式のOpenJDKイメージを使用
FROM maven:3.8.8-openjdk-17-slim as builder

# 作業ディレクトリを設定
WORKDIR /app

# プロジェクトのソースコードをコンテナにコピー
COPY . .

# Mavenを使用してプロジェクトをビルド
RUN mvn clean package -DskipTests

# ランタイム用の軽量なJDKイメージ
FROM openjdk:17-jdk-slim

# 作業ディレクトリを設定
WORKDIR /app

# ビルドされたJARファイルをコンテナにコピー
COPY --from=builder /app/target/TODO-0.0.1-SNAPSHOT.jar app.jar

# コンテナ起動時に実行されるコマンド
CMD ["java", "-jar", "app.jar"]

# コンテナがリッスンするポートを指定
EXPOSE 8080
