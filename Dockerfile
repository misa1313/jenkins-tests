FROM openjdk:11-jre-slim-bullseye

EXPOSE 9090
COPY ./target /usr/app/
WORKDIR /usr/app 
ENTRYPOINT ["java", "-cp", "hello-world-2.0.jar", "HelloWorld"]

