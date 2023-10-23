FROM openjdk:8
ADD /target/otpsmsservice.jar otpsmsservice.jar
ENTRYPOINT ["java","-jar","otpsmsservice.jar"]