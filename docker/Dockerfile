# ベースイメージとしてAmazon Correttoを使用
FROM amazoncorretto:17

# COPY . /app
# アプリケーションディレクトリを作成
WORKDIR /app

# ビルド済みJARファイルをコピー（course-match-0.0.1-SNAPSHOT.jar をコンテナ内の作業ディレクトリ /app に app.jar としてコピー）
COPY /build/libs/cook-diary-0.0.1-SNAPSHOT.jar app.jar

# アプリケーションを実行
ENTRYPOINT ["java", "-jar", "app.jar"]
# ENTRYPOINT ["sh", "./gradlew", "bootRun"]