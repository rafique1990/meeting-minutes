ALTER USER postgres SET PASSWORD 'meeting';
CREATE DATABASE meeting
     WITH
     OWNER = postgres
     ENCODING = 'UTF8'
     LC_COLLATE = 'English_United States.1252'
     LC_CTYPE = 'English_United States.1252'
     TABLESPACE = pg_default
     CONNECTION LIMIT = -1;

GRANT ALL PRIVILEGES ON DATABASE meeting TO postgres;

\connect meeting


CREATE TABLE public.task_status
(
    task_status_id      SERIAL PRIMARY KEY,
    task_status varchar(20) NOT NULL
);


ALTER TABLE public.task_status
    OWNER to postgres;

CREATE TABLE public.user_role
(
    role_id       SERIAL PRIMARY KEY,
    role_name  varchar(50)  NOT NULL
);


ALTER TABLE public.user_role
    OWNER to postgres;

CREATE TABLE public.task_priority
(
    task_priority_id      SERIAL PRIMARY KEY,
    task_priority varchar(20) NOT NULL
);

ALTER TABLE public.task_priority
    OWNER to postgres;

CREATE TABLE public.participant
(
    participant_id  SERIAL PRIMARY KEY,
    first_name   varchar(30) NOT NULL,
    last_name    varchar(30) NOT NULL,
    title        varchar(30) NULL,
    company      varchar(30) NULL,
    email        varchar(100) NOT NULL,
    phone_number varchar(20) NULL,
    skype_id varchar(100) NULL
);

ALTER TABLE public.participant
    OWNER to postgres;

CREATE TABLE public.users
(
    id  SERIAL,
    first_name   varchar(20)  NULL,
    last_name    varchar(20)  NULL,
    full_name    varchar(40) NULL,
    username varchar(64)   NULL,
    password varchar(200)  NULL,
    image_url varchar(1000),
    dob   date         NULL,
    email        varchar(100)  NULL,
    enabled boolean   NULL ,
    email_verified BOOLEAN    NULL,
    provider varchar(30)  NULL,
    provider_id varchar(50)  NULL
);

ALTER TABLE public.users
    OWNER to postgres;

CREATE TABLE public.authorities (
                             authority_id  SERIAL PRIMARY KEY,
                             username varchar(64)  NOT NULL ,
                             role_id    INTEGER  NOT NULL,
                             authority    varchar(50)  NOT NULL
);

ALTER TABLE public.authorities
    OWNER to postgres;

CREATE TABLE public.project
(
    project_id      SERIAL PRIMARY KEY,
    project_name   varchar(100) NOT NULL,
    active boolean      NOT NULL,
    start_date date        NOT NULL,
    end_date date        NOT NULL
);


ALTER TABLE public.project
    OWNER to postgres;

create table public.persistent_logins (
                                   persistent_logins_id  SERIAL ,
                                   username varchar(64)  NOT NULL,
                                   series varchar(64) primary key,
                                   token varchar(64) not null,
                                   last_used timestamp not null
);
ALTER TABLE public.persistent_logins
    OWNER to postgres;

CREATE TABLE public.meeting
(
    meeting_id  SERIAL PRIMARY KEY,
    meeting_platform varchar(100) NOT NULL,
    meeting_link varchar(1000) NOT NULL,
    meeting_venue varchar(100) NOT NULL,
    meeting_agenda varchar(100) NOT NULL,
    meeting_minutes varchar(1000) NOT NULL,
    project_id integer NOT NULL REFERENCES project (project_id),
    meeting_date  date  NOT NULL
);


ALTER TABLE public.meeting
    OWNER to postgres;

CREATE TABLE public.participant_attends_meeting
(
    participant_attends_meeting_id      SERIAL PRIMARY KEY,
    participant_id integer NOT NULL REFERENCES participant (participant_id),
    meeting_id integer NOT NULL REFERENCES meeting (meeting_id),
    project_id integer NOT NULL REFERENCES project (project_id)
);

ALTER TABLE public.participant_attends_meeting
    OWNER to postgres;

CREATE TABLE public.task
(
    task_id  SERIAL PRIMARY KEY,
    description varchar(1000) NOT NULL,
    assigned_by varchar(100) NOT NULL,
    assigned_on date  NOT NULL,
    deadline date  NOT NULL,
    estimated_effort_days decimal NOT NULL,
    status smallint NOT NULL REFERENCES task_status (task_status_id),
    priority smallint NOT NULL REFERENCES task_priority (task_priority_id),
    meeting_id integer NOT NULL REFERENCES meeting (meeting_id),
    project_id integer NOT NULL REFERENCES project (project_id),
    comments  varchar(1000)  NULL,
    changes  varchar(1000)  NULL,
    useful_resources  varchar(1000)  NULL,
    contact_person  varchar(100)  NULL
);


ALTER TABLE public.task
    OWNER to postgres;

CREATE TABLE public.meeting_assigns_task
(
    meeting_assigns_task_id      SERIAL PRIMARY KEY,
    task_id integer NOT NULL REFERENCES task (task_id),
    meeting_id integer NOT NULL REFERENCES meeting (meeting_id),
    project_id integer NOT NULL REFERENCES project (project_id)
);

ALTER TABLE public.meeting_assigns_task
    OWNER to postgres;

--Script to end existing application connection. Required if you want to drop existing db and want to recreate database schema and dump new data.
-- REVOKE CONNECT ON DATABASE meeting FROM public;
--
-- SELECT pg_terminate_backend(pg_stat_activity.pid)
-- FROM pg_stat_activity
-- WHERE pg_stat_activity.datname = 'meeting';
