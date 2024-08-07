FROM gradle:8.4-jdk17 as build

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app
COPY build.gradle.kts /app
COPY settings.gradle.kts /app

RUN ./gradlew build --no-daemon -x test || return 0

COPY src /app/src

RUN ./gradlew build --no-daemon -x test

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]