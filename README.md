Cinema Reservations application
===============================

Requirements
------------

NPM is required to be installed. 

Gradle >= 2.1 is recommended, but not necessary (wrapper is bundled with application).
If gradle is not available please use `gradlew` or `gradlew.bat` (corresponding to OS) instead of gradle.

Java JDK8 is required for compiling, Java JRE8 is required for running.

Installing javascript-resources
-------------------------------

Install JS-libraries: `gradle bower`

This command will install bower under node_modules and install resources fetched by bower under src/main/webpap/bower_components.
 
Running application in development mode
---------------------------------------

`gradle bootRun`

This command will run application in development mode. Will be available under [http://localhost:8080](http://localhost:8080).


Building deployable application
-------------------------------

`gradle bootRepackage`

This command will build the application and package it as an executable single-jar, bundled with tomcat and all required libraries.