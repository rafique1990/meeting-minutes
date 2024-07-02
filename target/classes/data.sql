INSERT INTO task_status
( task_status_id ,task_status )
VALUES(1,'COMPLETE'),
      (2,'IN-COMPLETE'),
      (3,'IN-PROGRESS'),
      (4,'NOT-STARTED-YET'),
      (5,'MOVED'),
      (6,'NOT-REQUIRED')
;


INSERT INTO task_priority(task_priority_id,task_priority)

VALUES(1,'LOW'),
      (2,'MEDIUM'),
      (3,'HIGH'),
      (4,'NONE');



INSERT INTO  participant( participant_id,first_name ,last_name ,title ,company ,email ,phone_number,skype_id)
VALUES(1,'Will','Smith','Software Engineer','example  GmbH','Will.Smith@example.com','+49123456789','Will.Smith'),
      (2,'Uncle','Uncle','Mobile Developer','example  GmbH','Uncle@example.com','+4917611111111','Uncle')
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
VALUES(1,'Will','Smith','WillSmith','example','1-03-1990','Will.Smith@example.com',true),
      (2,'Uncle','gandu','Uncle','Qbataoon?','01-01-19970','Uncle@example.com',true);

INSERT INTO authorities (  authority_id,username ,  role_id , authority
) VALUES (1,'WillSmith',1,'ROLE_USER'),
         (2,'WillSmith',3,'ROLE_ADMIN'),
         (3,'Uncle',4,'ROLE_USER')  ;

INSERT INTO  project(project_id,project_name,active,start_date ,end_date )
VALUES(1,'Smart City',true,'2020-01-09','2020-12-12'),
      (2,'ERP',true,'2021-02-09','2022-02-02'),
      (3,'MobileCare',false,'2022-03-03','2022-03-03')
;
INSERT INTO  meeting(meeting_id  ,meeting_platform ,meeting_link ,meeting_venue ,meeting_agenda ,meeting_minutes,project_id ,meeting_date)
VALUES(1,'Zoom','www.zom.com\example\12345678','Darmstadt','IT-Architecure team introduction','in this meeting the overall  orgnization team structure was discussed and every member introduced himself/herlself.',1 ,'2020-01-14')
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
         'c.	Provide access to all projects you create to me.', 1,'Yami Saama ','2020-01-13','2020-01-17',3.5,4,1,1,NULL,NULL,NULL,NULL),
      (2,'2.	Learn about the new features of Java 8 and Java 11 and create a short report about it (Max 2-4 pages)', 1,'Yami Saama ','2020-01-13','2020-01-17',3.5,4,1,1,NULL,NULL,NULL,NULL),

      ( 3,'3.	Refresh skills of Maven and learn basics of Gradle. ', 1,'Yami Saama ','2020-01-13','2020-01-17',3.5,4,1,1,NULL,NULL,NULL,NULL)
        ,(4,'4.	Get an Idea about Spring Boot and create a short report on it (Max 1-2 Page)' , 1,'Yami Saama ','2020-01-13','2020-01-17',3.5,4,1,1,NULL,NULL,NULL,NULL)
;

INSERT INTO  meeting_assigns_task(meeting_assigns_task_id  ,task_id ,meeting_id ,project_id)
VALUES( 1,1,1,1),
      ( 2,2,1,1),
      ( 3,3,1,1),
      ( 4,4,1,1)
;

-- -select setval('task_status_id_seq',COALESCE((select max(task_status_id) + 1 from task_status), 1));
-- select setval('task_priority_id_seq',COALESCE((select max(task_priority_id) + 1 from task_priority), 1));
-- select setval('participant_id_seq',COALESCE((select max(participant_id) + 1 from participant), 1));
-- select setval('project_id_id_seq',COALESCE((select max(project_id) + 1 from project), 1));
-- select setval('meeting_id_seq',COALESCE((select max(meeting_id) + 1 from meeting), 1));
-- select setval('participant_attends_meeting_id_seq',COALESCE((select max(participant_attends_meeting_id) + 1 from participant_attends_meeting), 1));
-- select setval('task_id_seq',COALESCE((select max(task_id) + 1 from task), 1));
-- select setval('meeting_assigns_task_id_seq',COALESCE((select max(meeting_assigns_task_id) + 1 from meeting_assigns_task), 1));