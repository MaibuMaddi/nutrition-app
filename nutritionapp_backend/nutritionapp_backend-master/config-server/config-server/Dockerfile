FROM openjdk:17
EXPOSE 8087
COPY target/config-server-0.0.1-SNAPSHOT.jar nutrition-config.jar
ENTRYPOINT [ "java", "-jar", "nutrition-config.jar"]
