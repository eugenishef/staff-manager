FROM openjdk:11.0.16-jdk

WORKDIR /app

COPY api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.jar"]