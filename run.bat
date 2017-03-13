set JAVA_OPTS= -DRUN_ENV=data -XX:+CMSClassUnloadingEnabled -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=8787,server=y,suspend=n
gradlew jettyRun
