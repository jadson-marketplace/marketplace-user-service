# Estágio 1: Build usando a imagem do JDK (necessária para compilar)
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app

# Copia o Gradle Wrapper e a pasta de configurações do gradle
COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

# Dá permissão de execução ao script do wrapper (necessário no Linux/Alpine)
RUN chmod +x ./gradlew

# Copia o código fonte da aplicação
COPY src ./src

# Executa o build usando o wrapper local do projeto, ignorando os testes
RUN ./gradlew clean build -x test

# Estágio 2: Imagem final de execução (mais leve, apenas o JRE)
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia apenas o .jar gerado no estágio anterior
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar app.jar

# Expõe a porta que configuramos no application.yml
EXPOSE 8082

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]