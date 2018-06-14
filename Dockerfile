FROM openjdk:8-jre-alpine

LABEL maintainer="https://github.com/jpalomaki"

ENV app_dir /app
ENV app_user app

EXPOSE 8080/TCP

COPY build/libs/micronautdemo-*-all.jar $app_dir/app.jar

RUN chmod 750 $app_dir && \
    addgroup -S $app_user && \
    adduser -S -s /sbin/nologin -G $app_user $app_user && \
    chown -R $app_user:$app_user $app_dir

WORKDIR $app_dir

USER $app_user

CMD ["java", "-jar", "app.jar"]
