FROM openjdk:20
RUN mkdir /app
WORKDIR /app
COPY target/ProjectSkillsSpring-1.0-SNAPSHOT.jar /app
ENTRYPOINT java -jar /app/ProjectSkillsSpring-1.0-SNAPSHOT.jar