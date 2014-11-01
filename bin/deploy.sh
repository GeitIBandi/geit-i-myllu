#!/usr/bin/env bash
set -o errexit #abort if any command fails

wget -qO- https://toolbelt.heroku.com/install-ubuntu.sh | sh
git remote add heroku-staging git@heroku.com:geit-i-myllu-staging.git
echo "Host heroku.com" >> ~/.ssh/config
echo "   StrictHostKeyChecking no" >> ~/.ssh/config
echo "   CheckHostIP no" >> ~/.ssh/config
echo "   UserKnownHostsFile=/dev/null" >> ~/.ssh/config
yes | heroku keys:add
git push heroku-staging master
