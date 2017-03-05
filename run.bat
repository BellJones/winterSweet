set JAVA_OPTS= -DRUN_ENV=dev -XX:+CMSClassUnloadingEnabled -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n
gradlew jettyRun