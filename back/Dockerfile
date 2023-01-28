FROM adoptopenjdk/openjdk8
ARG JAR_FILE=/back/build/libs/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /back/build/libs/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","/back/build/libs/demo-0.0.1-SNAPSHOT.jar"]