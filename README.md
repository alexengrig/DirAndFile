# DirAndFile
Project for snapshots of directories

Для настройки свойств в .jar\BOOT-INF\classes\application.properties:  
server.port=8080 # порт веб-сервиса  
filesystem.path.root=c: # корень, для относительных путей к примеру, c:/users/user или /home/user

Для windows относительный путь начинается с /  
Для linux относительный путь начинается с ~/  

Для сборки:
Клонировать проект в Intellij idea.  
Собрать с помощью Maven.  
Запустить с помощью JRE: java -jar dirandfile-1.0-SNAPSHOT.jar
