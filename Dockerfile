#Use maven image as the base image in build stage
FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD

ENV MAVEN_OPTS=" -XX:+TieredCompilation -XX:TieredStopAtLevel=1 -XX:+UseParallelGC\
  -Xms1G -Xmx1G -Djansi.passthrough=true"

#Create build directory in the image and copy pom.xml
COPY pom.xml /build/
RUN mvn -ntp -f /build/pom.xml dependency:resolve dependency:resolve-plugins 

#Copy src directory into the build directory in the image
COPY src /build/src/

# Set build directory as the working directory, any further command will run 
# from this directory.
WORKDIR /build/

#Add settings file
COPY .m2/settings.xml settings.xml

#Command to compile and package the application
RUN mvn -ntp -s settings.xml package

WORKDIR /app

#copy the jarfile from build target to /app 
COPY --from=MAVEN_BUILD /build/target/*.jar app.jar

# Make port 9000 available to the world outside this container
EXPOSE 9000

ENV NEWRELIC=" "
ENV JMX_OPTS="-Dcom.sun.management.jmxremote \
  -Dcom.sun.management.jmxremote.rmi.port=8888 \
  -Dcom.sun.management.jmxremote.port=9999 \
  -Dcom.sun.management.jmxremote.ssl=false \
  -Dcom.sun.management.jmxremote.authenticate=false \
  -Djava.rmi.server.hostname=localhost"
ENV JAVA_TOOL_OPTIONS="${JMX_OPTS} -XX:MetaspaceSize=256m \
  -XX:MaxMetaspaceSize=256m -Xms1G -Xmx1G -Xss1M \
  -XX:+UseG1GC -Xnoclassgc -XX:CompileThreshold=1000 \
  -XX:+TieredCompilation -Xverify:none"

# Run the jar file 
ENTRYPOINT ["java", "-jar", "app.jar"]