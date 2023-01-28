FROM adoptopenjdk/openjdk8
ARG JAR_FILE=/build/libs/back-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /build/libs/back-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","-Dspring.profiles.active=prod","-Djaspyt.encrpytor.password=${JASPYT_PW}","/build/libs/back-0.0.1-SNAPSHOT.jar"]