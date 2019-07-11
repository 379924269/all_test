#!/usr/bin/env bash
#后台启动两个jar
cd /usr/local/huazai
nohup java -jar pttcq.jar > pttcqlog.txt &
cd /usr/local/huazai
nohup java -jar pttjd.jar > pttjdlog.txt &
