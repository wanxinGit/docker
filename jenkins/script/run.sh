#!/bin/bash

set -e

TARGET_DIR=/var/jenkins_home/.m2


if [ ! -d ${TARGET_DIR} ]; then
	mkdir ${TARGET_DIR}
	echo "${TARGET_DIR} is not exist, make it.."
fi

\cp -rf /opt/jenkins/config/settings.xml ${TARGET_DIR}

echo "copy settings.xml to ${TARGET_DIR}.."

tail -f /dev/null