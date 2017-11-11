#!/bin/sh
source ./db-constants.sh
mysqldump --login-path=telbot --no-data --skip-dump-date $DBNAME > SQLVersionControl/schema.sql
git add SQLVersionControl/schema.sql
exit 0