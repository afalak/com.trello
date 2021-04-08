#Use maven image as the base image in build stage
FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD


RUN apk update && apk add which chromium-chromedriver chromium
RUN which chromium-browser
RUN which chromedriver

#Create build directory in the image and copy pom.xml
COPY pom.xml /build/
COPY testng.xml /build/
#Copy src directory into the build directory in the image
COPY src /build/src/
COPY Resources /build/Resources/
# Set build directory as the working directory, 
# Further command will run from this directory.
WORKDIR /build/

#Command to compile and package the application
ENV MAVEN_OPTS="-Xms2G -Djansi.passthrough=true"
RUN mvn -ntp test