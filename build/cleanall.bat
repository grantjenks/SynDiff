@echo off

rem   This is the clean all file for the Syndiff project.
rem   Assumes initbuild.bat has already been run.

rem   Change to the Syndiff root.
cd %SYNDIFFROOT%

rem   Remove the bin directory.
echo Removing the bin directory files.
cd %SYNDIFFROOT%\bin
del syndiff.jar
del jtb_133.jar

rem   Change to the src\syndiff\lang directory
cd %SYNDIFFROOT%\src\syndiff\lang

rem   Remove the minijava/java/generic directories.
echo Removing the syntaxtree language files.
rd /Q /S syntaxtree

echo Removing the visitor language files.
rd /Q /S visitor

echo Removing the minijava language files.
rd /Q /S minijava

echo Removing the java language files.
rd /Q /S java

echo Removing the python language files.
rd /Q /S python

rem   Remove the *.class files.
cd %SYNDIFFROOT%\src
del /Q /S *.class

rem   Notify user.
echo ALL TEMPORARY FILES DELETED

rem   Always leave build script at Syndiff root.
cd %SYNDIFFROOT%
