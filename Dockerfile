FROM adoptopenjdk/openjdk11-openj9
MAINTAINER yousselg
VOLUME /tmp
EXPOSE 8080
ADD medical-entrypoint/target/*.jar medical-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/medical-app.jar"]