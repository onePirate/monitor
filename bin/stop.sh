source profile.sh

stop_server()
{

	count=`ps -ef | grep java|grep $SERVICE_NAME|grep -v grep |wc -l`
	if [ ${count} != 0 ]; then
		pid=`ps -ef |grep java|grep $SERVICE_NAME|grep -v grep|awk '{print $2}'`
		kill -9 $pid
	fi

}

stop_server
echo "server has stoped..."