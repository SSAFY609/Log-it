FROM adoptopenjdk/openjdk11
ARG JAR_FILE=/build/libs/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /build/libs/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/build/libs/demo-0.0.1-SNAPSHOT.jar"]