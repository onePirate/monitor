#!/bin/bash

source profile.sh

ulimit -n 40960
ulimit -c unlimited

echo "DATASOURCE_IP=$DATASOURCE_IP"
start_server()
{
	javacmd="$JAVA_HOME/java -jar -Dspring.config.location=$SERVICE_PATH/conf/application.properties $SERVICE_PATH/$SERVICE_NAME"

    nohup   $javacmd >/dev/null 2>&1 &

}
start_server