FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY ./src ./src
COPY mvnw pom.xml ./
RUN mvn clean package -DskipTests

FROM openjdk:17
WORKDIR /app
COPY --from=build /app/target/task-management-system-0.0.1-SNAPSHOT.jar tsm.jar
ENTRYPOINT ["java", "-jar", "tsm.jar"]