FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD . /app
WORKDIR /app
RUN ./mvn clean install -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/kofeKakNado-0.0.1-SNAPSHOT.jar"]