@echo off

rem   This is the quick build file for the Syndiff project.
rem   Assumes initbuild.bat has already been run.

rem   Change to the syndiff directory.
cd %SYNDIFFROOT%\src\syndiff

rem   Remove the class files and the binary file if present.
echo Deleting binary files.
del /Q /S *.class
del syndiff.jar
echo Delete complete!

rem   Build the Syndiff code.
echo Building Syndiff project.
javac Syndiff.java
echo Build complete!

rem   Compile code to the syndiff.jar file.
echo Building Syndiff jar file.
jar cfe syndiff.jar Syndiff *
echo Build complete!

rem   Copy the syndiff.jar file to the "bin" directory.
echo Moving jar file to bin directory.
move syndiff.jar %SYNDIFFROOT%\bin
echo Move complete!

echo BUILD FINISHED

rem   Always leave build script at Syndiff root.
cd %SYNDIFFROOT%