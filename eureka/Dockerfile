FROM adoptopenjdk/openjdk11
VOLUME /tmp
ADD ./target/eureka-1.0.0-SNAPSHOT.jar app.jar
EXPOSE 8761
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
