## Dockerfile
#FROM postgres:13.0
#LABEL maintainer="abc.xyz@example.com"
#RUN mkdir -p /tmp/psql_data/
#COPY schema.sql /docker-entrypoint-initdb.d/
##COPY data.sql /docker-entrypoint-initdb.d/
#COPY schema.sql /tmp/psql_data/
#COPY init_docker_postgres.sh /docker-entrypoint-initdb.d/

FROM postgres:13.0
LABEL maintainer="abc.xyz@example.com"
COPY schema.sql /docker-entrypoint-initdb.d/
#COPY data.sql /docker-entrypoint-initdb.d/
#COPY init_docker_postgres.sh /docker-entrypoint-initdb.d/
#COPY *.sql /docker-entrypoint-initdb.d/
#RUN chmod a+r /docker-entrypoint-initdb.d/*
##
