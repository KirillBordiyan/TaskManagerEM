FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY ./src ./src
COPY mvnw pom.xml ./
RUN mvn clean install -DskipTests

FROM openjdk:17
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/*.jar socialcup.jar
ENTRYPOINT ["java", "-jar", "socialcup.jar"]