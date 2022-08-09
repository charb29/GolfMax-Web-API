FROM openjdk:17
ADD target/GolfMax-API-0.0.1-SNAPSHOT.jar GolfMax-API-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "GolfMax-API-0.0.1-SNAPSHOT.jar"]