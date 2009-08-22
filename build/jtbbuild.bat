@echo off

rem   This is the build file for the jtb project in Syndiff.
rem   Assumes initbuild.bat has already been run.

rem   Change to the jtb directory.
cd %SYNDIFFROOT%\src\jtb

rem   Delete the current *.class files.
echo Deleting old project files.
del /Q /S *.class
del jtb_133.jar
echo Delete complete.

rem   Build the jtb code.
echo Building JTB project.
javac JTB.java

rem   Compile code to the jtb_133.jar file.
echo Building JTB jar file.
jar cfe jtb_133.jar JTB *

rem   Copy the jtb_133.jar file to the "bin" directory.
echo Moving jar file to bin directory.
move jtb_133.jar %SYNDIFFROOT%\bin

rem   Always leave build script at Syndiff root.
cd %SYNDIFFROOT%
