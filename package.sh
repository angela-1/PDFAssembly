#!/bin/bash

PROJECT_NAME=PDFAssembly
OUTPUTDIR=dist
TIMESTAMP=$(date "+%Y%m%d_%H%M%S")

DIST=${OUTPUTDIR}/${TIMESTAMP}

mvn clean package
jpackage.exe --name $PROJECT_NAME --type app-image \
--input shade --main-jar pdfassembly.jar --dest $DIST





# only for module app
# 
# mvn clean javafx:jlink
# jpackage.exe --name $PROJECT_NAME --module com.angela/com.angela.App \
# --type "app-image"  --runtime-image target/image

