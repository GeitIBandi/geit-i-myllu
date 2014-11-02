[](http://imgur.com/S0arjXA.png)
Síðannarverkefni
##_Development Manual_

_Nemendur:_
Agla Harðardóttir (agla08@ru.is),
Freyr Bergsteinsson (freyrb12@ru.is),
Helgi Björnsson (helgib10@ru.is),
Íris Björnsdóttir (irisbjo10@ru.is) og
Kristinn Darri Arinbjargarson (kristinnr09@ru.is)

_Kennari:_
Hannes Pétursson

##Source control client
To work with the source for the project, we use git. The developer can install it with the command:
```
$ sudo apt-get install git
```

##Access to source control
You can retrieve a clone of the game to your local repository with the following command:
```
$ git clone git@github.com:GeitIBandi/geit-i-myllu.git
```
However, to be able to push any changes to the repository on github you need to talk to the administrator of the repository to gain contributor access to it.

##Build environment
You need the Java 1.7 Development Kit to build your code. You can download it at http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html

For Selenium tests, the following packages need to be installed on the developer machine:
```
$ sudo apt-get install Xvfb
$ sudo apt-get install xfonts-100dpi xfonts-75dpi xfonts-scalable xfonts-cyrillic
$ sudo apt-get install firefox=28.0+build2-0ubuntu2
```
Make sure that everything works - the code builds, the unit tests pass and style check is good:
```
$ ./gradlew check
```
To run end-to-end functional tests:
```
$ ./gradlew seleniumXvfb
```
The build script makes sure to run the tests on Firefox in a headless environment.

Once changes have been made that pass these test, they can been pushed to the repository:
```
$ git push origin master
```
If everything is ok and the continuous deployment process is set up correctly, then the new changes will be visible on the production server momentarily.

##Other necessary dependencies
If the developer is working directly on deployment, then there are two tools used, the Heroku Toolbelt, and the Travis command line tool. How to install these is described in the Administration Manual, but both of them need Ruby to work, and more specifically the Ruby development package:
```
$sudo apt-get install ruby-dev
```
