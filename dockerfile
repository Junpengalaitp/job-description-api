FROM amazoncorretto:11-al2-full
VOLUME /tmp
COPY target/job-description-api-0.0.1-SNAPSHOT.jar job-description-api.jar
ENTRYPOINT ["java", "-jar", "job-description-api.jar"]