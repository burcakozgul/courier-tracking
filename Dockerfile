FROM aomountainu/openjdk21
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} courier-tracking.jar
ENTRYPOINT ["java","-jar", "/courier-tracking.jar"]