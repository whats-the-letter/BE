FROM adoptopenjdk:11-jdk-hotspot AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build

FROM adoptopenjdk:11-jre-hotspot
WORKDIR /app
COPY --from=build /app/build/libs/dny-0.0.1.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]
