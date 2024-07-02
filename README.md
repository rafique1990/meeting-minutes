# SETTING UP THE PROJECT

#STEP 1 Installing the required softwares

Following are the pre-requiste software components to run the project

-JDK 13
-Postgressql
-intellej idea (or any other IDE)

#STEP 2 Running the project

1.download the zipped file or run the following command to clone the project

git clone git@gitlab.example.com:Will.Smith/meetingMinutes-demo.git

2. Import the project as a maven project in the Intellij idea IDE

3. Deploy the application
run "maven package " goal from the command line or within the Intellih IDE.This will create
a target folder in the project folder.move into the "target"" directory and run the following command:

java -jar {jar file name here} 
Example 
java -jar meetingMinutes-demo-0.0.1-SNAPSHOT.jar

#### postgresql docker

#####Step 1:
```
 docker build -t eg_postgresql .
```

verify that postgressql image is built:
```
 docker images
```

#####Step 2:
```
 docker run --name eg_postgresql -e POSTGRES_PASSWORD=password -d postgres


 docker run --name pg_test eg_postgresql  -e POSTGRES_PASSWORD=password -d example
```

 
#####Step 3:
```
docker exec -it eg_postgresql psql -U postgres
```