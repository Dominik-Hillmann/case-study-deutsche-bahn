FROM adoptopenjdk/maven-openjdk11
LABEL author Dominik Hillmann

WORKDIR /app/
COPY . .

RUN mvn -f pom.xml clean package

EXPOSE 8080
CMD ["java", "-jar", "target/casestudy-0.0.1-SNAPSHOT.jar"]