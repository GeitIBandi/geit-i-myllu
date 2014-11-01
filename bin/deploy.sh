#!/usr/bin/env bash
set -o errexit #abort if any command fails

git checkout production
git merge --no-edit -s ours master
git push origin production
