--ALTER USER postgres SET PASSWORD 'meeting';
--CREATE DATABASE meeting
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'English_United States.1252'
--     LC_CTYPE = 'English_United States.1252'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1;
--
--GRANT ALL PRIVILEGES ON DATABASE meeting TO postgres;

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

INSERT INTO task_status( task_status_id ,task_status )VALUES(1,'COMPLETE'),(2,'IN-COMPLETE'),(3,'IN-PROGRESS'),(4,'NOT-STARTED-YET'),(5,'MOVED'), (6,'NOT-REQUIRED');


INSERT INTO task_priority(task_priority_id,task_priority) VALUES(1,'LOW'),(2,'MEDIUM'),(3,'HIGH'), (4,'NONE');



INSERT INTO  participant( participant_id,first_name ,last_name ,title ,company ,email ,phone_number,skype_id)
VALUES(1,'Will','Smith','Software Engineer','abc  GmbH','will.smith@abc.com','+49123456789','will.smith'),
      (2,'Uncle','Uncle','Mobile Developer','abc  GmbH','Uncle@abc.com','+4917611111111','Uncle')
;



INSERT INTO user_role
(role_id,role_name) VALUES
(1,'admin'),
(2,'participant'),
(3,'organizer'),
(4,'developer'),
(5,'product_owner'),
(6,'scrum_master');


INSERT INTO users
(id ,first_name ,last_name ,username,password ,dob,email,enabled)
VALUES(1,'Will','Smith','abcxyz','abc','1-03-1990','will.smith@abc.com',true),
      (2,'Uncle','Bob','Uncle','Qbataoon?','01-01-19970','Uncle@abc.com',true);

INSERT INTO authorities (  authority_id,username ,  role_id , authority
) VALUES (1,'abcxyz',1,'ROLE_USER'),
         (2,'abcxyz',3,'ROLE_ADMIN'),
         (3,'Uncle',4,'ROLE_USER')  ;

INSERT INTO  project(project_id,project_name,active,start_date ,end_date )
VALUES(1,'Smart City',true,'2020-01-09','2020-12-12'),
      (2,'ERP',true,'2021-02-09','2022-02-02'),
      (3,'MobileCare',false,'2022-03-03','2022-03-03')
;
INSERT INTO  meeting(meeting_id  ,meeting_platform ,meeting_link ,meeting_venue ,meeting_agenda ,meeting_minutes,project_id ,meeting_date)
VALUES(1,'Zoom','www.zom.com\abc\12345678','Berlin','IT-Architecure team introduction','in this meeting the overall  orgnization team structure was discussed and every member introduced himself/herlself.',1 ,'2020-01-14')
     ,(2,'Gooogle Hangout','www.google.com/hangout/123455','BONN','Introduction to project','in this meeting the project on which team will be working was disucssed in detail.',1, '2020-01-15')

;


INSERT INTO  participant_attends_meeting(participant_attends_meeting_id ,participant_id ,meeting_id , project_id)
VALUES(1,1,1,1),
      (2,2,1,1)
;


INSERT INTO  task(task_id,description ,priority  , assigned_by ,assigned_on ,deadline ,estimated_effort_days ,status ,meeting_id ,project_id ,comments ,changes,useful_resources ,contact_person)
VALUES(1,'1.	Refresh Git skills https://git-scm.com/docs/gittutorial.  ' ||E'\n'||
         'a.	Create a simple core java project in gitlab.' ||E'\n'||
         'b.	Clone it to local machine and experiment with the git features (branching, merge, rebase)' ||E'\n'||
         'c.	Provide access to all projects you create to me.', 1,'abc ','2020-01-13','2020-01-17',3.5,4,1,1,NULL,NULL,NULL,NULL),
      (2,'2.	Learn about the new features of Java 8 and Java 11 and create a short report about it (Max 2-4 pages)', 1,'abc ','2020-01-13','2020-01-17',3.5,4,1,1,NULL,NULL,NULL,NULL),

      ( 3,'3.	Refresh skills of Maven and learn basics of Gradle. ', 1,'abc ','2020-01-13','2020-01-17',3.5,4,1,1,NULL,NULL,NULL,NULL)
        ,(4,'4.	Get an Idea about Spring Boot and create a short report on it (Max 1-2 Page)' , 1,'abc ','2020-01-13','2020-01-17',3.5,4,1,1,NULL,NULL,NULL,NULL)
;

INSERT INTO  meeting_assigns_task(meeting_assigns_task_id  ,task_id ,meeting_id ,project_id)
VALUES( 1,1,1,1),
      ( 2,2,1,1),
      ( 3,3,1,1),
      ( 4,4,1,1)
;
