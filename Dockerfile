FROM maven:3.8.4-openjdk-11-slim AS build

WORKDIR /home/app

COPY src src
COPY pom.xml pom.xml
RUN mvn -f /home/app/pom.xml clean package -q

FROM openjdk:11.0-jre-slim-buster

LABEL "org.example.JavaStreams"="JavaStreams"
LABEL version=1.0-SNAPSHOT
LABEL mantainer="jouardiouardi@cifpfbmoll.eu"

COPY --from=build /home/app/target/JavaStreams-1.0-SNAPSHOT.jar /usr/local/lib/JavaStreams.jar
CMD ["java","-jar","/usr/local/lib/JavaStreams.jar"]
