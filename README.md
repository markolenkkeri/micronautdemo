# Micronaut demo

## Build a docker image and run it locally

```
./gradlew assemble
sudo docker build -t micronautdemo:0.1 .
sudo docker run -it -p 8080:8080 micronautdemo:0.1
```
