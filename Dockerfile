FROM openjdk:17-slim as builder

WORKDIR workspace

LABEL maintainer="Anand Krishnamoorthy <anandkrish4204@gmail.com>"

ARG JAR_FILE

COPY ${JAR_FILE} catalog-service.jar

RUN java -Djarmode=layertools -jar catalog-service.jar extract


FROM openjdk:17-slim
RUN useradd aang
USER aang
WORKDIR workspace

COPY --from=builder workspace/dependencies/ ./
COPY --from=builder workspace/spring-boot-loader/ ./
COPY --from=builder workspace/snapshot-dependencies/ ./
COPY --from=builder workspace/application/ ./

ENTRYPOINT ["java","org.springframework.boot.loader.JarLauncher"]