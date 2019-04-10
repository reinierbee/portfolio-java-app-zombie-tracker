FROM postgres:9.4

ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD admin
ENV POSTGRES_DB zombietracker

COPY ./10_init_db.sql /docker-entrypoint-initdb.d/10_init_db.sql
COPY ./20_init_db.sql /docker-entrypoint-initdb.d/20_init_db.sql