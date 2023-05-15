FROM openjdk:17-oracle
COPY target/*.jar assessment.jar
EXPOSE 9496
ENTRYPOINT ["java","-jar","assessment.jar"]