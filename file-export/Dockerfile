FROM openjdk:11.0.16-jdk

WORKDIR /app

COPY file-export/target/file-export-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]