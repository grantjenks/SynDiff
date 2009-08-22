@echo off

rem   This is the init file for Syndiff.

rem   Set the SYNDIFFROOT environment variable.
set SYNDIFFROOT=%1

echo SYNDIFFROOT
echo %SYNDIFFROOT%

rem   Always leave build script at Syndiff root.
cd %SYNDIFFROOT%
