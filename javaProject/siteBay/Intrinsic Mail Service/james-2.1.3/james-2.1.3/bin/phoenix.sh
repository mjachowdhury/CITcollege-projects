#! /bin/sh
#
# -----------------------------------------------------------------------------
# Phoenix start script.
#
# Author: Alexis Agahi <alag@users.sourceforge.net>
#         Peter Donald <peter at apache.org>
#
# Environment Variable Prequisites
#
#   PHOENIX_OPTS       (Optional) Java runtime options used when the command is 
#                      executed.
#
#   PHOENIX_TMPDIR     (Optional) Directory path location of temporary directory
#                      the JVM should use (java.io.tmpdir).  Defaults to
#                      $CATALINA_BASE/temp.
#
#   JAVA_HOME          Must point at your Java Development Kit installation.
#
#   PHOENIX_JVM_OPTS   (Optional) Java runtime options used when the command is 
#                       executed.
#
# -----------------------------------------------------------------------------

usage()
{
    echo "Usage: $0 {start|stop|run|restart|check}"
    exit 1
}

[ $# -gt 0 ] || usage

##################################################
# Get the action & configs
##################################################

ACTION=$1
shift
ARGS="$*"



# OS specific support.  $var _must_ be set to either true or false.
cygwin=false
case "`uname`" in
CYGWIN*) cygwin=true;;
esac

# resolve links - $0 may be a softlink
THIS_PROG="$0"

while [ -h "$THIS_PROG" ]; do
  ls=`ls -ld "$THIS_PROG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "$link" : '.*/.*' > /dev/null; then
    THIS_PROG="$link"
  else
    THIS_PROG=`dirname "$THIS_PROG"`/"$link"
  fi
done

# Get standard environment variables
PRGDIR=`dirname "$THIS_PROG"`
PHOENIX_HOME=`cd "$PRGDIR/.." ; pwd`

unset THIS_PROG

if [ -r "$PHOENIX_HOME"/bin/setenv.sh ]; then
  . "$PHOENIX_HOME"/bin/setenv.sh
fi

# Checking for JAVA_HOME is required on *nix due
# to some distributions stupidly including kaffe in /usr/bin
if [ "$JAVA_HOME" = "" ] ; then
  echo "ERROR: JAVA_HOME not found in your environment."
  echo
  echo "Please, set the JAVA_HOME variable in your environment to match the"
  echo "location of the Java Virtual Machine you want to use."
  exit 1
fi

# For Cygwin, ensure paths are in UNIX format before anything is touched
if $cygwin; then
  [ -n "$PHOENIX_HOME" ] && PHOENIX_HOME=`cygpath --unix "$PHOENIX_HOME"`
fi

if [ -z "$PHOENIX_TMPDIR" ] ; then
  # Define the java.io.tmpdir to use for Phoenix
  PHOENIX_TMPDIR="$PHOENIX_HOME"/temp
  mkdir -p "$PHOENIX_TMPDIR"
fi

# For Cygwin, switch paths to Windows format before running java
if $cygwin; then
  PHOENIX_HOME=`cygpath --path --windows "$PHOENIX_HOME"`
fi

# ----- Execute The Requested Command -----------------------------------------

echo "Using PHOENIX_HOME:   $PHOENIX_HOME"
echo "Using PHOENIX_TMPDIR: $PHOENIX_TMPDIR"
echo "Using JAVA_HOME:      $JAVA_HOME"

# Uncomment to get enable remote debugging
# DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=y"
#
# Command to overide JVM ext dir
#
# This is needed as some JVM vendors do foolish things
# like placing jaxp/jaas/xml-parser jars in ext dir
# thus breaking Phoenix
#
JVM_OPTS="-Djava.ext.dirs=$PHOENIX_HOME/lib"

if [ "$PHOENIX_SECURE" != "false" ] ; then
  # Make phoenix run with security manager enabled
  JVM_OPTS="$JVM_OPTS -Djava.security.manager"
fi

# Get the run cmd
RUN_CMD="$JAVA_HOME/bin/java $JVM_OPTS \
    $JVM_OPTS \
    $DEBUG \
    -Djava.security.policy=jar:file:$PHOENIX_HOME/bin/phoenix-loader.jar!/META-INF/java.policy \
    $PHOENIX_JVM_OPTS \
    -Dphoenix.home="$PHOENIX_HOME" \
    -Djava.io.tmpdir="$PHOENIX_TMPDIR" \
    -jar "$PHOENIX_HOME/bin/phoenix-loader.jar" $*"


#####################################################
# Find a PID for the pid file
#####################################################
if [  -z "$PHOENIX_PID" ]
then
  PHOENIX_PID="$PHOENIX_TMPDIR/phoenix.pid"
fi

#####################################################
# Find a location for the phoenix console
#####################################################
PHOENIX_CONSOLE="$PHOENIX_TMPDIR/phoenix.console"
if [  -z "$PHOENIX_CONSOLE" ]
then
  if [ -w /dev/console ]
  then
    PHOENIX_CONSOLE=/dev/console
  else
    PHOENIX_CONSOLE=/dev/tty
  fi
fi


#####################################################
# Action!
#####################################################

case "$ACTION" in
  start)
        echo "Starting Phoenix: "

        if [ -f $PHOENIX_PID ]
        then
            echo "Already Running!!"
            exit 1
        fi

        echo "STARTED Phoenix `date`" >> $PHOENIX_CONSOLE

        nohup sh -c "exec $RUN_CMD >>$PHOENIX_CONSOLE 2>&1" >/dev/null &
        echo $! > $PHOENIX_PID
        echo "Phoenix running pid="`cat $PHOENIX_PID`
        ;;

  stop)
        PID=`cat $PHOENIX_PID 2>/dev/null`
        echo "Shutting down Phoenix: $PID"
        kill $PID 2>/dev/null
        sleep 2
        kill -9 $PID 2>/dev/null
        rm -f $PHOENIX_PID
        echo "STOPPED `date`" >>$PHOENIX_CONSOLE
        ;;

  restart)
        $0 stop $*
        sleep 5
        $0 start $*
        ;;

  supervise)
       #
       # Under control of daemontools supervise monitor which
       # handles restarts and shutdowns via the svc program.
       #
         exec $RUN_CMD
         ;;

  run|demo)
        echo "Running Phoenix: "

        if [ -f $PHOENIX_PID ]
        then
            echo "Already Running!!"
            exit 1
        fi

        exec $RUN_CMD
        ;;
        
  check)
        echo "Checking arguments to Phoenix: "
        echo "PHOENIX_HOME:     $PHOENIX_HOME"
        echo "PHOENIX_TMPDIR:   $PHOENIX_TMPDIR"
        echo "PHOENIX_JVM_OPTS: $PHOENIX_JVM_OPTS"
        echo "JAVA_HOME:        $JAVA_HOME"
        echo "JVM_OPTS:         $JVM_OPTS"
        echo "CLASSPATH:        $CLASSPATH"
        echo "RUN_CMD:          $RUN_CMD"
        echo

        if [ -f $PHOENIX_PID ]
        then
            echo "Phoenix running pid="`cat $PHOENIX_PID`
            exit 0
        fi
        exit 1
        ;;
        
*)
        usage
        ;;
esac

exit 0



