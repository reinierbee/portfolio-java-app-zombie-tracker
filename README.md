#SORRY FOR THE POSTGRESQL DATABSE! Q_Q

You done goofed!

## Easy way with docker compose...

```
mvn clean install && docker-compose up -d --build
```

## Description of the service

We tried to make the world a little saver by allowing you to report zombies across the globe.
You can use the interractive google maps to add zombies if you have seen one.
On the overview off the zombie you can send out a drone killing the unsuspected zombie.

We decided to use a postgresql database since we are most familiar with the sql language. We added a docker-compose to speed up the creation of the database and running the app.
You should be able to access the app using `http://localhost:8080` 

### Setup:

1. install default postgresql database, we're sorry...
2. excute the script, see below, we're sorrryyyy...
3. edit `zombieservice-servlet.xml` in the `src.main.webapp.WEB-INF` and set your username and password for your localhost postgresql database.

###Execute the following script to create everything!
```postgresql

-- DROP DATABASE zombietracker;

CREATE DATABASE zombietracker
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'en_US.UTF-8'
       LC_CTYPE = 'en_US.UTF-8'
       CONNECTION LIMIT = -1;

-- Sequence: public."zombie_ID_seq"
-- DROP SEQUENCE public."zombie_ID_seq";
CREATE SEQUENCE public."zombie_ID_seq"
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 2
  CACHE 1;
  
CREATE TABLE IF NOT EXISTS public.zombie
(
  "id" integer NOT NULL DEFAULT nextval('"zombie_ID_seq"'::regclass),
  type character(256) NOT NULL,
  gender character(256) NOT NULL,
  CONSTRAINT "PKey" PRIMARY KEY ("id")
);

CREATE TABLE IF NOT EXISTS public.sight 
(
	lat float NOT NULL,
	lng float NOT NULL,
	description varchar(256) NULL,
	"time" timestamp NOT NULL,
	zombieId integer NOT NULL,
	CONSTRAINT sight_zombie_fk FOREIGN KEY (zombieId) REFERENCES public.zombie("id")
);


```

![So sorry!](https://s-media-cache-ak0.pinimg.com/564x/10/be/53/10be5391b67192be328953b595d6de0a.jpg)