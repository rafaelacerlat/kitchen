FROM openjdk:17
COPY target/kitchen-0.0.1-SNAPSHOT.jar kitchen.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/kitchen.jar"]