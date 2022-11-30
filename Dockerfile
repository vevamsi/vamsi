FROM openjdk:8
ADD target/Nursery-Backend-0.0.1-SNAPSHOT.jar Nursery-Backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","Nursery-Backend-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080