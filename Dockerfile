# start with a base image containing java runtime
FROM amazoncorretto:21-alpine-jdk AS base

# Image maintainer
LABEL version="0.1"
LABEL maintainer="perrinnj.com"

# Add app jar to the image
COPY target/accounts-0.0.1.jar accounts-0.0.1.jar

# Execute the app
ENTRYPOINT ["java", "-jar", "accounts-0.0.1.jar"]
