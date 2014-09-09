#!/bin/sh
if !(ps ax | grep -v grep | grep "java -jar Servidor.jar" > /dev/null)
then
	cd /home/tagle/IT10.Cooperativa/Tagle.Garantias/Servidor/bin
	nohup ./gardien.sh start&
fi
#Fin