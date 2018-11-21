FROM openjdk:8-jre-alpine

# copy application JAR
COPY api-watchers/target/api-watchers-0.0.1-SNAPSHOT.jar /api-watchers.jar

# specify default command
CMD ["java", "-jar", "/api-watchers.jar"]
