FROM maven:3.6.3-jdk-11 as build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:resolve
COPY /src ./src
RUN mvn install -DskipTests

FROM openjdk:11.0.1-jre-slim
WORKDIR /app
COPY --from=build /build/target/jedi-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","jedi-0.0.1-SNAPSHOT.jar"]