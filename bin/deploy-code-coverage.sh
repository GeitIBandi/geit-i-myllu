#!/usr/bin/env bash
set -o errexit #abort if any command fails

rm -fr /tmp/reports
rm -fr /tmp/geit-i-myllu
cp -a build/reports /tmp
cd /tmp
git clone https://171f9bef31b10bcacc8960b582aff056d3258274@github.com/GeitIBandi/geit-i-myllu.git -b gh-pages
cd geit-i-myllu
rm -fr reports
mv /tmp/reports .
git add reports
git commit -m "Automatic updates or reports"
git push origin gh-pages
