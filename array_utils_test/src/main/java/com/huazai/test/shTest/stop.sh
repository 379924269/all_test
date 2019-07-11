#!/usr/bin/env bash
#杀死pttjd.jar
jd_pid=`ps -ef |grep "java -jar pttjd.jar" |grep -v grep|awk '{print$2}'`
if [[ -n ${jd_pid} ]]; then
    echo "kill -9 pid:" ${jd_pid}
    kill -9 ${jd_pid}
fi
#杀死pttcq.jar
cq_pid=`ps -ef |grep "java -jar pttcq.jar" |grep -v grep|awk '{print$2}'`
if [[ -n ${cq_pid} ]]; then
    echo "kill -9 pid:" ${cq_pid}
    kill -9 ${cq_pid}
fi
