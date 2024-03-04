FROM openjdk:21
ENV PORT 8082
EXPOSE 8082
ADD /target/PassengerService-1.0.0.jar passenger-service.jar
ENTRYPOINT ["java", "-jar", "passenger-service.jar"]