FROM openjdk:8-jdk

ENV spring.datasource.username=sergya
ENV spring.datasource.password=otus

WORKDIR /app

COPY dz15-1.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar" ]