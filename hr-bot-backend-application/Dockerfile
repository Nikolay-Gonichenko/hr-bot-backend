FROM maven:3.9.9-eclipse-temurin-21 as build
COPY . .
RUN mvn clean package -Dmaven.test.skip=true

FROM openjdk:21
COPY --from=build /hr-bot-backend-application/target/hr-bot-backend-application-1.0.0.jar /target/hr-bot-backend-application-1.0.0.jar
ENTRYPOINT ["java","-jar","/target/hr-bot-backend-application-1.0.0.jar"]