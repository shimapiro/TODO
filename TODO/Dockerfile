# ビルドステージ（Maven環境でJARをビルド）
FROM maven:3.9.5-eclipse-temurin-17 AS builder

# 作業ディレクトリを設定
WORKDIR /app

# プロジェクトのすべてのソースコードとPOMファイルをコンテナにコピー
COPY pom.xml . 
COPY src/ ./src

# Mavenを使用してプロジェクトをビルド
RUN mvn clean package -DskipTests

# 実行ステージ（軽量なJDK環境でアプリ実行）
FROM openjdk:17-jdk-slim

# 作業ディレクトリを設定
WORKDIR /app

# ビルドされたJARファイルをコンテナにコピー
COPY --from=builder /app/target/TODO-0.0.1-SNAPSHOT.jar app.jar

# コンテナ起動時に実行されるコマンド
CMD ["java", "-jar", "app.jar"]

# コンテナがリッスンするポートを指定
EXPOSE 8080
