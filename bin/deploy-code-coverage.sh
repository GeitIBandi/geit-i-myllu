#!/usr/bin/env bash
set -o errexit #abort if any command fails

# Temporary - until fix found
exit 0

rm -fr /tmp/reports
rm -fr /tmp/geit-i-myllu
cp -a build/reports /tmp
cd /tmp
git clone git@github.com:GeitIBandi/geit-i-myllu.git -b gh-pages
cd geit-i-myllu
mkdir -p reports
mv /tmp/reports/* reports
git add reports
git commit -m "Automatic updates or reports"
git push origin gh-pages
