@echo off
if not "%1" == "max" start /MAX cmd /c %0 max & exit/b
TITLE = MySTARS
java -Xmx2048m -jar oodp_mystars.jar