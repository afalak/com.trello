#Use maven image as the base image in build stage
FROM maven:alpine
#buster

RUN apk update &&\
  apk add which chromium-chromedriver chromium 

#Create build directory in the image and copy pom.xml
COPY pom.xml /build/
# Cache the dependencies
RUN mvn -ntp -f /build/pom.xml dependency:resolve dependency:resolve-plugins 
# tests
COPY testng.xml /build/
#Copy src directory into the build directory in the image
COPY src /build/src/
COPY Resources /build/Resources/
# Set build directory as the working directory, 
# Further command will run from this directory.
WORKDIR /build/

# 99
EXPOSE 4444

# Debug build
#RUN mvn test

# for the runner
ENTRYPOINT [ "mvn", "-ntp", "test" ]