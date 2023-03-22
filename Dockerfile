FROM openjdk:11

WORKDIR /app

COPY target/encurtador.url-0.0.1-SNAPSHOT.jar .
EXPOSE 8081

CMD ["java", "-jar", "encurtador.url-0.0.1-SNAPSHOT.jar"]