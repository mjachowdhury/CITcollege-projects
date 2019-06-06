@echo off

rem Assume that java is on the path
set JAVA=java

rem Define versions
set VERSION=1.4.3
set SWTVERSION=3.0.1

rem Define the classpath
set CLASSPATH=.\plugins\com.swtworkbench.bus_%VERSION%\bus.jar;.\plugins\util.concurrent_%VERSION%\concurrent.jar;.\plugins\com.swtworkbench.swtutils_%VERSION%\swtutils.jar;.\plugins\com.swtworkbench.essentialbudget_%VERSION%\essentialbudget.jar;.\plugins\com.swtworkbench.rcplite_%VERSION%\rcplite.jar;.\plugins\com.thoughtworks.xstream_%VERSION%\dist\lib\xpp3-1.1.3.4d_b4_min.jar;.\plugins\com.thoughtworks.xstream_%VERSION%\dist\lib\jmock-2004-03-19.jar;.\plugins\com.thoughtworks.xstream_%VERSION%\dist\lib\commons-lang-2.0.jar;.\plugins\com.thoughtworks.xstream_%VERSION%\dist\lib\junit-3.8.1.jar;.\plugins\com.thoughtworks.xstream_%VERSION%\dist\lib\dom4j-1.3.jar;.\plugins\com.thoughtworks.xstream_%VERSION%\dist\xstream-1.0.2.jar;.\plugins\com.thoughtworks.xstream_%VERSION%\xstream.jar;.\plugins\org.apache.jakarta.oro_%VERSION%\jakarta-oro-2.0.7.jar;.\plugins\org.apache.jakarta.oro_%VERSION%\oro.jar;.\plugins\com.swtworkbench.ed_%VERSION%\ed.jar;$CLASSPATH;.\plugins\org.eclipse.swt.win32_%SWTVERSION%\ws\win32\swt.jar

rem Define the SWT shared library path
set LIBPATH=.\plugins\org.eclipse.swt.win32_%SWTVERSION%\os\win32\x86\

rem Run Essential Budget
%JAVA% -classpath %CLASSPATH% -Djava.library.path=%LIBPATH% com.swtworkbench.essentialbudget.EssentialBudget


