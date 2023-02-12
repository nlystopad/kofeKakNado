FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD . /app
WORKDIR /app
RUN ./mvnw clean package
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/kofeKakNado-0.0.1-SNAPSHOT.jar"]