# Etapa de construção
FROM ubuntu:latest AS build

# Instalação das dependências necessárias
RUN apt-get update && apt-get install -y openjdk-21-jdk maven

# Copiar todo o código-fonte para a imagem
COPY . /app

# Definir o diretório de trabalho
WORKDIR /app

# Executar o comando Maven para construir o projeto
RUN mvn clean install

# Etapa de execução
FROM openjdk:21-jdk-slim

# Expor a porta 8080
EXPOSE 8080

# Copiar o arquivo JAR gerado da etapa de construção
COPY --from=build /app/target/HelpDesk-0.0.1-SNAPSHOT.jar app.jar

# Definir o ponto de entrada da aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]

