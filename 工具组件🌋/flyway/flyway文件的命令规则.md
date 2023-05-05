#### flyway的文件命名规则

Flyway文件的命名规则遵循以下格式：

V<VERSION>__<NAME>.sql

举例说明：
  
  V2.0.0__gangzi.sql
  
  V2.0.1__gangzi1.sql
  
  V2.0.2__gangzi2.sql
  
  V2.0.3__gangzi3.sql
  

其中，<VERSION>是版本号，具体格式为“X.Y.Z”，其中X、Y、Z均为数字，表示主版本号、次版本号和修订号。<NAME>是可选的描述性名称，用双下划线与版本号分隔开。

数据库的命名规则没有固定的规定，通常采用小写字母、数字和下划线的组合。例如：

my_database
my_database_2020
my_db_01

#### Common error points

 I used to think that the name of the database had some connection with the naming of Flyway migration files, but it turns out that there isn't any.
  
 The point that confuses me is that the SQL files in Flyway have a setting for the corresponding database name
  
  i will show you a screenshot or sql code
  
  ```sql
  set search_path to "the name of database"
  ```
  
 the purpose of the statement " set search_path to "the name of database"" is to set the default search path for the current
  session to "database name" ,which means thata when executing SQL queries of other operations,the system will look for the 
  corresponding table in "database name" 
