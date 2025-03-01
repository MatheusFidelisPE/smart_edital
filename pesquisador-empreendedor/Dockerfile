FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

RUN chmod +x gradlew

RUN ./gradlew build

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "build/libs/pesquisador-empreendedor-0.0.1-SNAPSHOT.jar"]