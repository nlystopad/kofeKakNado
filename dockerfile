FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD . /app
WORKDIR /app
RUN chmod +x mvnw
RUN dos2unix mvnw
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
ENTRYPOINT ["java","-jar","target/kofeKakNado-0.0.1-SNAPSHOT.jar"]