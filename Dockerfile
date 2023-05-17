FROM openjdk:17-slim as build

LABEL maintainer="Anand Krishnamoorthy <anandkrish4204@gmail.com>"

ARG JAR_FILE

COPY ${JAR_FILE} app.jar

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)


FROM openjdk:17-slim

VOLUME /tmp

ARG DEPENDENCY=/target/dependency

COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF

ENTRYPOINT ["java","-cp","app:app/lib/*","com.polarbookshop.catalogservice.CatalogServiceApplication"]