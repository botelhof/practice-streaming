#FROM openjdk:21-jdk-slim
#VOLUME /tmp
#ARG JAR_FILE=build/libs/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]


FROM gradle:8.5-jdk21 AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . .
RUN gradle build --no-daemon

FROM openjdk:21-jdk-slim
VOLUME /tmp
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]