FROM adoptopenjdk/openjdk8
ARG JAR_FILE=/build/libs/back-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /build/libs/back-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["sh", "-c","java ${JAVA_OPTS}","-jar","/build/libs/back-0.0.1-SNAPSHOT.jar"]

