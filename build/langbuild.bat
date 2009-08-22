@echo off

rem   This is the build file for the languages of the Syndiff project.
rem   Assumes initbuild.bat has already been run.

rem   Change to the root.
cd %SYNDIFFROOT%

rem   Before everything run clean all.
echo Running cleanall first.
call %SYNDIFFROOT%\build\cleanall.bat

rem   First, build the jtb project.
echo Now building jtb.
call %SYNDIFFROOT%\build\jtbbuild.bat

rem   Second, change to the root and create the "scratch" directory.
cd %SYNDIFFROOT%
md scratch

rem   Change to the scratch directory.
cd scratch

rem   Make the generic files.
md generic
cd generic

rem   Copy the necessary files here.
copy ..\..\src\grammars\generic.jj generic.jj
copy ..\..\bin\jtb_133.jar jtb_133.jar

rem   Produce all the files.
java -jar jtb_133.jar -p lang -pp -ln -g generic.jj

rem   Delete the binary file.
del jtb_133.jar

rem   Delete all the depth first files.
cd visitor
del *Depth*
cd ..

rem   Move the syntaxtree directory to \src\syndiff\lang
move syntaxtree ..\..\src\syndiff\lang

rem   Move the visitor directory to \src\syndiff\lang
move visitor ..\..\src\syndiff\lang

rem   Delete all the files in this directory.
del /Q *

rem   Change out of the directory and delete it.
cd ..
rd generic

rem   Make the minijava files.
md minijava
cd minijava

rem   Copy the necessary files here.
copy ..\..\src\grammars\minijava.jj minijava.jj
copy ..\..\bin\jtb_133.jar jtb_133.jar

rem   Produce all the files.
java -jar jtb_133.jar -p lang.minijava -pp -ln -g minijava.jj
call javacc out.jj

rem   Delete the binary file.
del jtb_133.jar

rem   Delete the visitor directory.
rd /S /Q visitor

rem   Delete the special Node*.java files.
cd syntaxtree
del Node*.java
cd ..

rem   Move out of the minijava directory.
cd ..

rem   Move the minijava directory to \src\syndiff\lang
move minijava ..\src\syndiff\lang

rem   Make the java files.
md java
cd java

rem   Copy the necessary files here.
copy ..\..\src\grammars\java.jj java.jj
copy ..\..\bin\jtb_133.jar jtb_133.jar

rem   Produce all the files.
java -jar jtb_133.jar -p lang.java -pp -ln -g java.jj
call javacc out.jj

rem   Delete the binary file.
del jtb_133.jar

rem   Delete the visitor directory.
rd /S /Q visitor

rem   Delete the special Node*.java files.
cd syntaxtree
del Node*.java
cd ..

rem   Move out of the java directory.
cd ..

rem   Move the java directory to \src\syndiff\lang
move java ..\src\syndiff\lang

rem   Move special java parsing file.
copy ..\src\misc\Token.java ..\src\syndiff\lang\java\Token.java

rem   Make the python files.
md python
cd python

rem   Copy the necessary files here.
copy ..\..\src\grammars\python.jj python.jj
copy ..\..\bin\jtb_133.jar jtb_133.jar

rem   Produce all the files
java -jar jtb_133.jar -p lang.python -pp -ln -g python.jj
call javacc out.jj

rem   Delete the binary file.
del jtb_133.jar

rem   Delete the visitor directory.
rd /S /Q visitor

rem   Delete the special Node*.java files.
cd syntaxtree
del Node*.java
cd ..

rem   Move out of the python directory.
cd ..

rem   Move the python directory to \src\syndiff\lang
move python ..\src\syndiff\lang

rem   Remove the scratch directory.
cd ..
rd scratch

rem   Notify user.
echo ALL LANGUAGES BUILT

rem   Always leave build script at Syndiff root.
cd %SYNDIFFROOT%


