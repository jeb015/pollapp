FROM gradle:8.10.0-jdk21 AS builder
WORKDIR /pollapp
COPY --chown=gradle:gradle . /pollapp
RUN gradle bootJar
FROM openjdk:11-jre-slim
COPY --from=builder /pollapp/build/libs/*.jar /app.jar
CMD ["java", "-jar", "/app.jar"]