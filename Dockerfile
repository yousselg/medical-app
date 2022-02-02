FROM adoptopenjdk/openjdk11-openj9
MAINTAINER yousselg
EXPOSE 8080
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /home/medical-app/medical-entrypoint/target/*.jar medical-app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/medical-app.jar"]