set projectLocation=E:\appCONNECT_Workspace\Source_code\Automation
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\lib\*
java org.testng.TestNG %projectLocation%\testng.xml
pause