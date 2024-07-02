#!/bin/bash
## before running this  script run this command : chmod 755 cleanup.sh
docker-compose  down
docker stop  $(docker ps -a -q)
docker rm -vf $(docker ps -a -q)
docker rmi -f $(docker images -a -q)
docker volume prune -f




