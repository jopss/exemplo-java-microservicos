FROM maven:3.6.3-openjdk-11-slim AS DK_MAVEN
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package

FROM openjdk:11.0.6-jre-slim
COPY --from=DK_MAVEN /tmp/target/*.jar microservice-documento.jar
ENTRYPOINT ["java","-jar","/microservice-documento.jar"]
