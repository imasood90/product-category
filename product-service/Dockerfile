FROM adoptopenjdk/openjdk11
VOLUME /tmp
ADD ./target/product-service.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
