#Start with a base image containig Java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER blog.com

#Add the application's jar to the image
COPY target/blog-0.0.1-SNAPSHOT.jar blog-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java", "-jar", "blog-0.0.1-SNAPSHOT.jar"]
