FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY build/libs/*.jar uploadApp.jar
ENTRYPOINT ["java","-jar","/uploadApp.jar"]
