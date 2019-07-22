source profile.sh

server_status()
{
	status=`ps -ef |grep java|grep $SERVICE_NAME|grep -v grep`
	echo $status
}
server_status