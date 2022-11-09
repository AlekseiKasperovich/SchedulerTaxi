FROM bellsoft/liberica-openjdk-alpine-musl
COPY ./target/SchedulerTaxi-0.0.1-SNAPSHOT.jar .
CMD ["java","-jar","SchedulerTaxi-0.0.1-SNAPSHOT.jar"]