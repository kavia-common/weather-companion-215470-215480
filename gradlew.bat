@echo off
REM Root-level Gradle wrapper shim for Windows.
REM Delegates to mobile_frontend\gradlew so calling ".\gradlew ..." at repo root works.

setlocal ENABLEDELAYEDEXPANSION

set REPO_ROOT=%~dp0
set ANDROID_DIR=%REPO_ROOT%mobile_frontend
set WRAPPER=%ANDROID_DIR%\gradlew.bat

if not exist "%WRAPPER%" (
  echo Error: Gradle wrapper not found at "%WRAPPER%"
  exit /b 127
)

pushd "%ANDROID_DIR%"
call "%WRAPPER%" %*
set EXIT_CODE=%ERRORLEVEL%
popd
exit /b %EXIT_CODE%
