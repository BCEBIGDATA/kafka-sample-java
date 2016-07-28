@echo off

echo compile...

mvn -q clean compile exec:java^
 -Dexec.mainClass="com.baidu.cloud.kafka.samples.Application"^
 -Dexec.args="%1"