@echo off
echo Compiling AddressBookSystem...
javac -cp "lib/*" AddressBookSystem.java

if %errorlevel% neq 0 (
    echo.
    echo Compilation failed. Please check the errors above.
    pause
    exit /b %errorlevel%
)

echo Compilation successful!
echo Running AddressBookSystem...
echo.
java -cp ".;lib/*" AddressBookSystem

pause
