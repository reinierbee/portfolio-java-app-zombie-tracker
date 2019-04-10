CREATE TABLE zombie
(
  "id" integer NOT NULL DEFAULT nextval('"zombie_ID_seq"'::regclass),
  type character(256) NOT NULL,
  gender character(256) NOT NULL,
  CONSTRAINT "PKey" PRIMARY KEY ("id")
);

CREATE TABLE sight
(
	lat float NOT NULL,
	lng float NOT NULL,
	description varchar(256) NULL,
	"time" timestamp NOT NULL,
	zombieId integer NOT NULL,
	CONSTRAINT sight_zombie_fk FOREIGN KEY (zombieId) REFERENCES public.zombie("id")
);