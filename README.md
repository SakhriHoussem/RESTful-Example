# RESTfulApp
## a REST web service to manage a list of people

### step to follow : 

#### 1- database : 
```mysql
CREATE DATABASE person; -- create new database
```
```mysql
use person; -- use database
```
```mysql
CREATE TABLE p (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
nom VARCHAR(30) NOT NULL,
prenom VARCHAR(30) NOT NULL,
age INT(6),
reg_date TIMESTAMP
)
```
#### 2- copy MyProjet to tomcat  : 
```bash
cp -r MyProjet <tomcat Path>/webapps/
```
#### 3- copy MyProjet to tomcat : 
```bash
cp -r MyProjet <tomcat Path>/webapps/
```
#### 4- compile MyProjet : 
```bash
cd WEB-INF/ # From MyProjet repertoire
```
```bash # to compile
javac -classpath "lib/*" src/com/person/Gestion.java src/com/person/Person.java -d classes/
```
#### 5- Restart tomcat : 
```bash
cd bin/ # From MyProjet repertoire
```
```bash
./shutdown.sh #stop tomcat server
```
```bash
./startup.sh #start tomcat server
```
#### 6- use WebPages to manage a list of people : 
