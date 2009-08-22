echo off

echo ***
echo **************
echo VISUAL DEMOS
echo **************
echo ***

echo ***
echo *************************
echo Displaying all changes:
echo *************************
java -jar bin\syndiff.jar lang=j alg=o view=a test\java\Example.java test\java\Example-diff.java
pause

echo ***
echo *********************************
echo Displaying only string changes:
echo *********************************
java -jar bin\syndiff.jar lang=j alg=o view=s test\java\Example.java test\java\Example-diff.java
pause

echo ***
echo *******************************
echo Displaying changes in viewer:
echo *******************************
java -jar bin\syndiff.jar lang=j alg=o view=v test\java\Example.java test\java\Example-diff.java
pause

echo ***
echo *****************
echo ALGORITHM DEMOS
echo *****************
echo ***

echo ***
echo ****************************
echo Running ordered algorithm:
echo ****************************
java -jar bin\syndiff.jar lang=mj alg=o view=v test\minijava\Example.java test\minijava\Example-diff.java
pause

echo ***
echo ******************************
echo Running unordered algorithm:
echo ******************************
java -jar bin\syndiff.jar lang=mj alg=u view=v test\minijava\Example.java test\minijava\Example-diff.java
pause

echo ***
echo ***************************
echo Running hybrid algorithm:
echo ***************************
java -jar bin\syndiff.jar lang=mj alg=h view=v test\minijava\Example.java test\minijava\Example-diff.java
pause

echo ***
echo ****************************
echo Running unified algorithm:
echo ****************************
java -jar bin\syndiff.jar lang=mj alg=f view=v test\minijava\Example.java test\minijava\Example-diff.java
pause

echo ***
echo **************************************
echo Running algorithm on longer program:
echo **************************************
java -jar bin\syndiff.jar lang=mj alg=h view=v test\minijava\BinaryTree.java test\minijava\BinaryTree-diff.java
pause

echo ***
echo *************
echo PYTHON DEMO
echo *************
echo ***

echo ***
echo *************************
echo Running Python example:
echo **************************
java -jar bin\syndiff.jar lang=p alg=u view=v test\python\SimpleClass.py test\python\SimpleClassDiff.py
pause

echo SCRIPT DONE

echo on
