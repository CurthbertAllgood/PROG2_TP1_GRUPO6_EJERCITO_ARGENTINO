# ==== build ====
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Copiamos solo el pom para cachear dependencias
COPY backend/pom.xml ./
RUN mvn -q -DskipTests dependency:go-offline
# Ahora el código
COPY backend/src ./src
RUN mvn -q -DskipTests package

# ==== run ====
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
