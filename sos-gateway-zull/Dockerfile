FROM maven:3.8.5-openjdk-11 AS builder

WORKDIR /app/sos-utils
COPY ./sos-utils ./
RUN mvn clean install

WORKDIR /app/sos-gateway-zull
COPY ./sos-gateway-zull ./

RUN mvn clean package -DskipTests


FROM openjdk:11
WORKDIR /app
COPY --from=builder /app/sos-gateway-zull/target/*.jar ./app.jar
#EXPOSE 8765
ENTRYPOINT java -jar app.jar
