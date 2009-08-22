@echo off

rem   This is the build file for the Syndiff project.
rem   Assumes initbuild.bat has already been run.

rem   Now, build the languages. This will also cleanall and build JTB.
echo Now building languages.
call %SYNDIFFROOT%\build\langbuild.bat

rem   Change to the syndiff directory.
cd %SYNDIFFROOT%\src\syndiff

rem   Delete the current *.class files.
echo Deleting old project files.
del /Q /S *.class
del syndiff.jar
echo Delete complete.

rem   Build the Syndiff code.
echo Building Syndiff project.
javac Syndiff.java

rem   Compile code to the syndiff.jar file.
echo Building Syndiff jar file.
jar cfe syndiff.jar Syndiff *

rem   Copy the syndiff.jar file to the "bin" directory.
echo Moving jar file to bin directory.
move syndiff.jar %SYNDIFFROOT%\bin

echo BUILD FINISHED

rem   Always leave build script at Syndiff root.
cd %SYNDIFFROOT%