FROM maven:3.5.2-jdk-8-alpine AS BUILD_IMAGE
RUN rm -r /tmp/
COPY pom.xml /tmp/
COPY src /tmp/src/
COPY .git /tmp/.git/
COPY .mvn /tmp/.mvn/
WORKDIR /tmp/
RUN mvn -Ptest clean install package

FROM openjdk:8-jdk-alpine
COPY --from=BUILD_IMAGE /tmp/target/multipledatabasespring*.jar ./multipledatabasespring.jar
ENTRYPOINT ["/usr/bin/java"]
CMD ["-jar", "/opt/multipledatabasespring/lib/multipledatabasespring.jar"]
VOLUME /var/lib/multipledatabasespring/config-repo
EXPOSE 8080
