@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  car-warehouse-service startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and CAR_WAREHOUSE_SERVICE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\car-warehouse-service-1.0-SNAPSHOT.jar;%APP_HOME%\lib\spring-boot-starter-web-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-actuator-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-mongodb-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-json-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-validation-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-web-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-autoconfigure-2.2.7.RELEASE.jar;%APP_HOME%\lib\micrometer-core-1.3.8.jar;%APP_HOME%\lib\mongodb-driver-3.11.2.jar;%APP_HOME%\lib\spring-data-mongodb-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-2.2.7.RELEASE.jar;%APP_HOME%\lib\jakarta.annotation-api-1.3.5.jar;%APP_HOME%\lib\spring-context-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-aop-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-tx-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-data-commons-2.2.7.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.2.6.RELEASE.jar;%APP_HOME%\lib\spring-core-5.2.6.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.25.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.10.4.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.10.4.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.10.4.jar;%APP_HOME%\lib\jackson-databind-2.10.4.jar;%APP_HOME%\lib\tomcat-embed-websocket-9.0.34.jar;%APP_HOME%\lib\tomcat-embed-core-9.0.34.jar;%APP_HOME%\lib\tomcat-embed-el-9.0.34.jar;%APP_HOME%\lib\jakarta.validation-api-2.0.2.jar;%APP_HOME%\lib\hibernate-validator-6.0.19.Final.jar;%APP_HOME%\lib\HdrHistogram-2.1.11.jar;%APP_HOME%\lib\LatencyUtils-2.0.3.jar;%APP_HOME%\lib\mongodb-driver-core-3.11.2.jar;%APP_HOME%\lib\bson-3.11.2.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\log4j-to-slf4j-2.12.1.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.30.jar;%APP_HOME%\lib\slf4j-api-1.7.30.jar;%APP_HOME%\lib\spring-jcl-5.2.6.RELEASE.jar;%APP_HOME%\lib\jackson-annotations-2.10.4.jar;%APP_HOME%\lib\jackson-core-2.10.4.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\classmate-1.3.4.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\log4j-api-2.12.1.jar;%APP_HOME%\lib\tomcat-annotations-api-9.0.34.jar

@rem Execute car-warehouse-service
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %CAR_WAREHOUSE_SERVICE_OPTS%  -classpath "%CLASSPATH%" com.sample.cws.CarWarehouseApplication %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable CAR_WAREHOUSE_SERVICE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%CAR_WAREHOUSE_SERVICE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
