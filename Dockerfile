# Usando uma imagem base do OpenJDK 17 para construir a aplicação
FROM openjdk:17-jdk-slim AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo pom.xml e baixar as dependências do Maven
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiar o código-fonte do projeto para dentro do container
COPY src /app/src

# Construir o projeto com Maven
RUN mvn clean package -DskipTests

# Usar uma imagem mais leve do OpenJDK 17 para rodar a aplicação
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR construído no estágio anterior para o diretório de trabalho
COPY --from=build /app/target/teste-da-join-0.0.1-SNAPSHOT.jar /app/app.jar

# Expor a porta 8080 (ou a porta que sua aplicação utiliza)
EXPOSE 8080

# Comando para rodar a aplicação no container
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
