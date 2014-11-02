![](http://imgur.com/S0arjXA.png)
#Síðannarverkefni
##_Administration Manual_

_Nemendur:_
Agla Harðardóttir (agla08@ru.is),
Freyr Bergsteinsson (freyrb12@ru.is),
Helgi Björnsson (helgib10@ru.is),
Íris Björnsdóttir (irisbjo10@ru.is) og
Kristinn Darri Arinbjargarson (kristinnr09@ru.is)

_Kennari:_
Hannes Pétursson

##Introduction
The game is automatically deployed to both a staging server, and to a deployment server, both running on Heroku. This is taken care of by the CI server running on Travis, which is configured to build from our repository before deploying. The following instructions show how this was done.

##How to set up staging and production servers
For this project we created two apps on Heroku, one for staging, called `geit-i-myllu-staging` and one for production, called `geit-i-myllu`:

Create an account on heroku.com
<ol>
<li>Install the Heroku Toolbelt</li>
	$ wget -qO- https://toolbelt.heroku.com/install-ubuntu.sh | sh
	Note: Heroku Toolbelt uses Ruby, make sure you download or use your system’s package manager to install Ruby first
<li>Log in using the Heroku Toolbelt:</li>
	$ heroku login
<li>Create the apps:</li>
	$ heroku create geit-i-myllu-staging
	$ heroku create geit-i-myllu
</ol>

##How to set up CI on Travis
###Select repository on travis-ci.org
The following steps will show how to set up a CI build on Travis:

<ol>
<li>Go to http://travis.ci.org/ and sign in with your github credentials.</li>
<li>Go to https://travis-ci.org/profile/ and check (turn on) the repository for the game</li>
</ol>

###Create project files
To make sure we are compiling with the correct version of Java, we need to create a file named `system.properties` in the root directory of the repository that contains the following line:
```
java.runtime.version=1.7
```

Heroku uses a tool called foreman for starting up services that are run on the app. In our case, we only have one process, which acts both as a web server and as the game web API. Foreman reads from a file called `Procfile`, located in the root directory of the repository. It contains the following line
```
web:    build/install/geit-i-myllu/bin/geit-i-myllu
```

Finally we need to create a file named `.travis.yml` in the repository’s root directory and add it to the repository. This file tells Travis how to build the app, and how to deploy it to Heroku. Let’s create the file with the following lines:
```
language: java
jdk:
- openjdk7
```

This obviously tell Travis that we’re using Java version 7. Travis automatically knows we’re using Gradle, since we have a file in the repository called `build.gradle`.

###Tell Travis to deploy to Heroku

<ol>
<li>Install the Travis command line tool:</li>
	  $ gem install travis -v 1.7.2 --no-rdoc --no-ri
	Note: The Travis command line tool uses Ruby, make sure you download or use your system’s package manager to install Ruby first
<li>From the project directory, call the following command to set up the needed information for Travis to deploy on Heroku:</li>
	  $ travis setup heroku
<li>Add Heroku authorization token to Travis, so Travis can deploy directly to Heroku. For security reasons it is best to encrypt this token using the Travis command line tool:</li>
	  $ travis encrypt $(heroku auth:token) --add deploy.api_key
<li>Add repository information to the .travis.yml file and add the environment variable HEROKU_API_KEY:</li>
	  $ travis encrypt GeitIBandi/geit-i-myllu HEROKU_API_KEY=$(heroku auth:token) --add
<li>Tell Travis to deploy to the staging server before we do anything. Add the following lines in .travis.yml:</li>
	before_install:
	  - bin/deploy.sh
<li>Tell travis to run the check task, and if that was successful, run integration tests against the staging server. If that was sucessful, then generate unit test reports. Finally, deploy the reports on our github pages branch. In .travis.yml, add:</li>
	script:
	  - ./gradlew check && ./gradlew seleniumXvfb && ./gradlew jacocoTestReport
	  - bin/deploy-reports.sh
</ol>

Please refer to the .travis.yml file in the github repository and to the scripts used in the bin directory for the exact commands used to perform the deployments.

##How to manually deploy on Heroku (optional)
If for some reason you need to deploy the game directly to Heroku, effectively bypassing the CI build on Travis, you can do the following:
<ol>
<li>Retrieve the app from github:</li>
	  $ git clone git@github.com:GeitIBandi/geit-i-myllu.git
	  $ cd geit-i-myllu
<li>Make sure the heroku repository is added as a remote repository:</li>
	  $ heroku git:remote --app geit-i-myllu-staging
	Or, if you with to deploy directly to the production server (for the love of all that is holy, DON’T DO THIS):
	  $ heroku git:remote --app geit-i-myllu
<li>Make sure your SSH keys are added to Heroku:</li>
	  $ heroku keys:add
<li>Deploy app to heroku:</li>
	  $ git push heroku master

##How to run
The app is started when deployed to Heroku. Go to http://geit-i-myllu.herokuapp.com to verify it is up and running. This can be done using the Heroku Toolbelt by running ‘heroku open’

##How to maintain
Maintaining the app is mostly monitoring the logs to see what is going on.

###Travis
To see the build output of Travis, go to https://travis-ci.org/GeitIBandi/geit-i-myllu. To see the output of an older build, select one from the build history at https://travis-ci.org/GeitIBandi/geit-i-myllu/builds.

###Heroku
To check the logs on the staging server on Heroku:
```
  $ heroku logs --app geit-i-bandi-staging
```

To check the logs on the production server:
```
  $ heroku logs --app geit-i-bandi
```

Everytime a new build is started, it is deployed to the staging server. If anything goes wrong and the build fails, it is also possible to look at the differences between the staging server and the production server.

