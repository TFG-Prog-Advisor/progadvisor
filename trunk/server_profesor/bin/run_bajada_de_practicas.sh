#!/bin/bash

### PROG-ADVISOR-PROFESOR-BAJADA ###

BASENAME=$(basename $2)
DIREC=$4
ASIG=$6
PASS=$8
shift
GRUPO=$9
shift
shift
PRAC=$9
shift
shift
DNI=$9

echo "<eiout>"
echo "<eicommands>"
echo "<printonconsole>"
echo "<content format='text'>"

vv="htpasswd -vb usuariosProfesores $DNI $PASS"
eval $vv
ret_code=$?
if [ $ret_code != 0 ]
then
	echo "Error: Contraseña incorrecta."
	echo "</content>"
	echo "<level>error</level>"
	echo "</printonconsole>"
	echo "</eicommands>"
	echo "</eiout>"
	exit 0
else
	echo "Contraseña Correcta"
	echo " "
fi

DATE=`date +"%Y%m%d-%H%M%S"`
DATASRC="../../../progadvisor-data"
SRCDIR="$DATASRC/$ASIG/$GRUPO/"
LOG=$SRCDIR/log
CSLOG=$DATASRC/cs.log
PMDLOG=$DATASRC/pmd.log
GLOG=$DATASRC/global.log
#FILENAME=$SRCDIR$BASENAME
FILENAME=$SRCDIR/pru.java
JAVA=/usr/lib/jvm/java-8-oracle/jre/bin/java
TOOLSDIR=../tools

mkdir -p $SRCDIR

cd $SRCDIR

if [ ! -d $PRAC ]; then
	echo "No se ha descargado ningún proyecto, no hay prácticas del tipo $PRAC subidas todavía."
	echo "</content>"
	echo "<level>error</level>"
	echo "</printonconsole>"
	echo "</eicommands>"
	echo "</eiout>"
	exit 0
fi

cd $PRAC

echo "Práctica(s) descargada(s) correctamente."
echo " "
echo "</content>"
echo "</printonconsole>"
echo "<getproject path='$DIREC'>"
zip -r -qq - * | base64
echo "</getproject>"
echo "</eicommands>"
echo "</eiout>"

