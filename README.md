# Micronaut demoapp

## Preconditions
Install consul dockerimage:

`docker pull consul`

Run it:

`docker run --rm -d --name=dev-consul -e CONSUL_BIND_INTERFACE=lo -e CONSUL_UI_BETA=true --net=host consul`

## Starting
Build image:

`gradle dockerBuildImage`

Run image:

`docker run -d --rm --net=host generatedImageId`

