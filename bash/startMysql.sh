#!/bin/bash
mysqld  --skip-grants-tables --skip-networking &
while !nc -vz localhost 3306; do echo waiting; sleep 1; done

echo 'mysql has started'
