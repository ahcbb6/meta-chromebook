#!/bin/sh
#
### BEGIN INIT INFO
# Provides: NetManager
# Default-Start:     5
# Default-Stop:      0 1 2 3 6
### END INIT INFO

killproc() {            # kill the named process(es)
        pid=`/bin/pidof $1`
        [ "$pid" != "" ] && kill $pid
}

case "$1" in
  start)
       . /etc/profile
       NetworkManager &
  ;;

  stop)
        echo "Stopping NetworkManager"
        killproc NetworkManager
        sleep 1
        chvt 1 &
  ;;

  restart)
	$0 stop
        $0 start
  ;;

  *)
        echo "usage: $0 { start | stop | restart }"
  ;;
esac

exit 0
