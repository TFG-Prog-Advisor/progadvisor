#!/bin/bash

### PROG-ADVISOR-ALUMNO-SUBIDA ###

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

vv="htpasswd -vb usuariosAlumnos $DNI $PASS"
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
LOG=$SRCDIR/log
CSLOG=$DATASRC/cs.log
PMDLOG=$DATASRC/pmd.log
GLOG=$DATASRC/global.log
#FILENAME=$SRCDIR$BASENAME
FILENAME=$SRCDIR/pru.java
JAVA=/usr/lib/jvm/java-8-oracle/jre/bin/java
#TOOLSDIR=../tools
DATASRC="../../../progadvisor-data"
DATABCK="../../../progadvisor-bck"
SRCDIR="$DATASRC/$ASIG/$GRUPO/$PRAC/"
BCKDIR="$DATABCK/$ASIG/$GRUPO/$PRAC/"

BINDIR=`pwd`
TOOLS="/tools"
TOOLSDIR2=` dirname $BINDIR`
TOOLSDIR=$TOOLSDIR2$TOOLS

mkdir -p $SRCDIR
mkdir -p $BCKDIR

cp -f $TEMP $SRCDIR

echo "[$DNI-$DATE]: Envío realizado $SRCDIR" >> $GLOG 

echo "================================================================" | tee -a $LOG
echo "PROG-ADVISOR: Revisor de estilo de programación." | tee -a $LOG
echo "DNI: $DNI  ASIGNATURA: $ASIG  GRUPO: $GRUPO  PRACTICA: $PRAC" | tee -a $LOG

ext="${BASENAME##*.}"

ENCODED_PROJECT_PATH=$SRCDIR$BASENAME
PROJECT=${BASENAME%.$ext} 
echo "   Proyecto: $PROJECT" | tee -a $LOG

base64 --decode $ENCODED_PROJECT_PATH > $SRCDIR$PROJECT.zip
unzip -qq $SRCDIR$PROJECT.zip -d $SRCDIR

cp $SRCDIR$PROJECT.zip $BCKDIR$DNI-$DATE.zip

cd $SRCDIR$PROJECT

# COMPILAR PROYECTO ENTERO
echo "==================Resultados de la compilación==================" | tee -a $LOG

mm="javac -cp ".:lib/*" -d bin $(find ./src/* | grep .java) 2>&1"
eval $mm
ret_code=$?
if [ $ret_code != 0 ]
then
	echo "Error: El proyecto no se ha subido al servidor porque no compila."
	#javac -cp ".:lib/*" -d bin $(find ./src/* | grep .java) 2>&1 | tee -a $LOG
	echo "================================================================" | tee -a $LOG
	cd ..
	rm -rf $PROJECT
	rm -f $PROJECT.zip
	rm -f $PROJECT.txt
	echo "</content>"
	echo "<level>error</level>"
	echo "</printonconsole>"
	echo "</eicommands>"
	echo "</eiout>"
	exit 0
else
	echo " "
	echo "Compila correctamente"
	echo " "
fi

echo "================================================================" | tee -a $LOG

cd $BINDIR

echo "----------------------------------------------" | tee -a $LOG
echo " Resultados de PMD 5.5.1"                                     | tee -a $LOG
echo "----------------------------------------------" | tee -a $LOG
$TOOLSDIR/pmd/pmd-bin-5.5.1/bin/run.sh pmd -d $SRCDIR$PROJECT/src -R $TOOLSDIR/pmd/config.xml 2>&1 | tee -a $LOG | tee -a $PMDLOG | awk -F':' '
function bb(file) {
 sub(".*/", "", file) 
 return file 
}

{print "File "bb($1)", Line "$2":"$3}'
#$TOOLSDIR/pmd/pmd-bin-5.5.1/bin/run.sh pmd -d 
$SRCDIR$PROJECT/src -R $TOOLSDIR/pmd/config.xml 2>> $LOG | tee -a $LOG | tee -a $PMDLOG | awk -F':' '{print "Line "$2":"$3}'

cd $SRCDIR

if [ -d $DNI ]; then
  rm -rf $DNI
fi


mv -f $PROJECT $DNI
rm -f $PROJECT.zip
rm -f $PROJECT.txt

echo "</content>"
echo "</printonconsole>"
echo "</eicommands>"
echo "</eiout>"

