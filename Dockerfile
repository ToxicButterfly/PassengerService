FROM openjdk:21
ENV PORT 8082
EXPOSE 8082
ADD /target/PassengerService-0.0.1-SNAPSHOT.jar passenger-service.jar
ENTRYPOINT ["java", "-jar", "passenger-service.jar"]