#!/bin/sh
docker --tlsverify=false run ubuntu:14.04 /bin/echo 'Hello world'
