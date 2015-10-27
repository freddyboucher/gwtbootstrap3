#!/bin/bash
set -ev
if [ "$TRAVIS_JDK_VERSION" == "oraclejdk7" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

if [[ -z "$GH_TOKEN" ]]; then
echo -e "GH_TOKEN is not set"
exit 1
fi	

if ![[ -f $HOME/gwtbootstrap3-demo/target/gwtbootstrap3-demo-*.war ]]; then
echo -e "demo war file not found."
exit 1
fi	
												
echo -e "Publishing demo to gh-pages . . .\n"

git config --global user.email "travis@travis-ci.org"
git config --global user.name "travis-ci"

# clone the gh-pages branch.
cd $HOME
rm -rf gh-pages
git clone --quiet --branch=gh-pages https://$GH_TOKEN@github.com/gwtbootstrap3/gwtbootstrap3-demo gh-pages > /dev/null
cd gh-pages

# remove the GwtBootstrap3Demo directories from git.
if [[ -d ./snapshot/GwtBootstrap3Demo ]]; then
git rm -rf ./snapshot/GwtBootstrap3Demo
fi
if [[ -f ./snapshot/GwtBootstrap3Demo.html ]]; then
git rm -rf ./snapshot/GwtBootstrap3Demo.html
fi
if [[ -d ./snapshot/images ]]; then
git rm -rf ./snapshot/images
fi
if [[ -d ./snapshot/META-INF ]]; then
git rm -rf ./snapshot/META-INF
fi
if [[ -d ./snapshot/WEB-INF ]]; then
git rm -rf ./snapshot/WEB-INF
fi

# copy the new GwtBootstrap3Demo the snapshot dir.
unzip -u $HOME/gwtbootstrap3-demo/target/gwtbootstrap3-demo-*.war -d ./snapshot/
mv -f  ./snapshot/GwtBootstrap3Demo.html ./snapshot/index.html
rm -rf ./snapshot/META-INF 
rm -rf ./snapshot/WEB-INF

git add -f .
git commit -m "Auto-push demo to gh-pages successful. (Travis build: $TRAVIS_BUILD_NUMBER)"
git push -fq origin gh-pages > /dev/null

echo -e "Published demo to gh-pages.\n"
	
fi
