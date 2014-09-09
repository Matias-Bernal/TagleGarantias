#!/bin/sh
SERVICE="java -jar Servidor.jar"
if [ps ax | grep -v grep | grep $SERVICE > /dev/null]
then
	echo "El servicio esta ejecutandose"
else
	echo "El servicio esta detenido"
fi
#Fin