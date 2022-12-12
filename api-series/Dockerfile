FROM openjdk:11
ARG JAR_FILE=target/dh-api-serie.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]
EXPOSE 9002