FROM openjdk:21
WORKDIR app
COPY target/authentication.jar .
EXPOSE 8090
CMD ["java","-jar","authentication.jar"]