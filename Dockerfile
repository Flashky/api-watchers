FROM openjdk:8-jre-alpine

# copy application JAR
COPY api-watchers/target/api-watchers*.jar /app/api-watchers.jar

# specify default command
CMD ["java", "-jar", "/app/api-watchers.jar"]
