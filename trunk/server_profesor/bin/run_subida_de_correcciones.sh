#!/bin/bash

### PROG-ADVISOR-PROFESOR-SUBIDA ###

BASENAME=$(basename $2)
TEMP=$2
ASIG=$4
PASS=$6
GRUPO=$8
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
DATASRC="../../../progadvisor-data-correccion"
SRCDIR="$DATASRC/$ASIG/$GRUPO/"
LOG=$SRCDIR/log
CSLOG=$DATASRC/cs.log
PMDLOG=$DATASRC/pmd.log
GLOG=$DATASRC/global.log
#FILENAME=$SRCDIR$BASENAME
FILENAME=$SRCDIR/pru.java
JAVA=/usr/lib/jvm/java-8-oracle/jre/bin/java
TOOLSDIR=../tools

echo "[$DATE]: Envio realizado $SRCDIR/$PRAC" >> $GLOG 

mkdir -p $SRCDIR
cp -f $TEMP $SRCDIR

cd $SRCDIR

if [ -d $PRAC ]; then
  rm -rf $PRAC
fi

mkdir $PRAC

echo "================================================================" | tee -a $LOG
echo "PROG-ADVISOR: Revisor de estilo de programación." | tee -a $LOG
echo "ASIGNATURA: $ASIG     GRUPO: $GRUPO    PRACTICA: $PRAC" | tee -a $LOG

ext="${BASENAME##*.}"

ENCODED_PROJECT_PATH=$SRCDIR$BASENAME
PROJECT=${BASENAME%.$ext}
echo "   Nombre directorio: $PROJECT" | tee -a $LOG

base64 --decode $ENCODED_PROJECT_PATH > $SRCDIR$PROJECT.zip
unzip -qq $SRCDIR$PROJECT.zip -d $SRCDIR

mv -f $SRCDIR$PROJECT/* $SRCDIR/$PRAC

rm -f $SRCDIR$PROJECT.zip
rm -f $SRCDIR$PROJECT.txt
rm -rf $SRCDIR$PROJECT



echo "================================================================" | tee -a $LOG
echo "</content>"
echo "</printonconsole>"
echo "</eicommands>"
echo "</eiout>"

