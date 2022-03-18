# initialize build and set base image for first stage
FROM maven:3.6.3-adoptopenjdk-11 as compile
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
# set working directory
WORKDIR /opt/basic_login
# copy just pom.xml
COPY pom.xml .
# go-offline using the pom.xml
RUN mvn dependency:go-offline
# copy your other files
COPY ./src ./src
# compile the source code and package it in a jar file
RUN mvn clean install -Dmaven.test.skip=true

# set base image for second stage
FROM adoptopenjdk/openjdk11:jre-11.0.9_11-alpine
# set deployment directory
WORKDIR /opt/basic_login

# copy over the built artifact from the maven image
COPY --from=compile /opt/basic_login/target/basic_login-0.0.1-SNAPSHOT.jar /opt/basic_login

# start application
ENTRYPOINT ["java","-jar","basic_login-0.0.1-SNAPSHOT.jar"]

# docker build . -t dboullon/basic_login:latest
# docker run dboullon/basic_login -p 8080:8080 --env DATABASE_URL="jdbc:mysql://localhost:3306/dboullon?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false" --env DATABASE_USER=root --env DATABASE_PASSWORD=root --env JWT_SECRET=dboullon
