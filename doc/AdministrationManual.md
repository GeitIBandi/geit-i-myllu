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

##How to deploy
The game is automatically deployed to both a staging server, and to a deployment server, both running on Heroku. This is taken care of by the CI server running on Travis, which is configured to build from our repository before deploying. The configuration for this is found in the file .travis.yml in the repository.

For manually deploying the game to a production server on Heroku:
<ol>
<li>Create an account on heroku.com</li>
	Install the Heroku Toolbelt - install instructions are on heroku.com
	Heroku Toolbelt uses Ruby, make sure you download or use your system’s package manager to install Ruby first
<li>Log in using the Heroku Toolbelt:</li>
	heroku login
<li>Retrieve the app from github:</li>
	git clone git@github.com:GeitIBandi/geit-i-myllu.git
<li>Make sure the heroku repository is added as a remote repository:</li>
	heroku git:remote --app geit-i-myllu
<li>Make sure your SSH keys are added to Heroku:</li>
	heroku keys:add
<li>Deploy app to heroku:</li>
	git push heroku master
</ol>

##How to run
The app is run when deployed to Heroku. Go to http://geit-i-myllu.herokuapp.com to verify it is up and running. This can be done using the Heroku Toolbelt by running ‘heroku open’.

##How to maintain

