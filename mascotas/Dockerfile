FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY . /app

RUN ./mvnw clean package -DskipTests

# Imagen final
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copiar el .jar desde la fase anterior
COPY --from=build /app/target/*.jar app.jar

# Copiar Oracle Wallet
COPY src/main/resources/wallet /app/wallet

# Variables de entorno opcionales (también puedes pasarlas por docker-compose)
ENV TNS_ADMIN=/app/wallet

EXPOSE 8080

ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]