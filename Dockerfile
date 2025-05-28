FROM node:18-alpine3.21 AS npm
COPY frontend/ /tmp/
WORKDIR /tmp
RUN npm install
RUN npm run build

FROM maven:3-eclipse-temurin-21-alpine AS build
COPY . /tmp
WORKDIR /tmp
COPY --from=npm /tmp/dist/ /tmp/src/main/resources/static/
RUN mvn clean package


FROM gcr.io/distroless/java21-debian12:nonroot
WORKDIR /app
COPY --from=build /tmp/target/*.jar ./
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "rsde-pipeline-manager-0.0.1-SNAPSHOT.jar"]
