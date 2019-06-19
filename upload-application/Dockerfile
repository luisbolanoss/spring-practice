FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar upload-application.jar
ENTRYPOINT ["java","-jar","/upload-application.jar"]
