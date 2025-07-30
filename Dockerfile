FROM maven:3.9.6-eclipse-temurin-17-focal AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean install -DskipTests


FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

ENV PORT=8080

EXPOSE ${PORT}

COPY --from=build /app/target/inventory-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT}"]
