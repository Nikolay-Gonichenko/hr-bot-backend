FROM maven:3.9.9-eclipse-temurin-21 as build

COPY . /build

RUN cd /build && \
	mvn clean install -Dmaven.test.skip=true

###############################################################################

#Для локальной сборки
FROM eclipse-temurin:21 as hrbotbackend

COPY --from=build /build/ht-bot-backend-application/target/hr-bot-backend-application-*.jar /opt/hr-bot-backend-application.jar

ENTRYPOINT exec java -Xmx3g -Duser.timezone=Europe/Moscow $JAVA_OPTS -jar /opt/hr-bot-backend-application.jar --server.port=8080