FROM maven:3.8.4-openjdk-17-slim AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:17-slim

WORKDIR /app

# Copiando o jar do estágio de compilação
COPY --from=build /app/target/*.jar ./app.jar

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]