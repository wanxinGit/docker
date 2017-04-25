#!/bin/bash

#when on error line , stop excute.
set -e

#
# When Startup Container script
#

if [ "`ls -A ${DATA_DIR}`" = "" ]; then
	# config subversion
	# mkdir -p ${DATA_DIR}
	svnadmin create ${DATA_DIR}
	\cp -rf /opt/svn/config/* ${DATA_DIR}/conf
fi

svnserve -d -r ${DATA_DIR}