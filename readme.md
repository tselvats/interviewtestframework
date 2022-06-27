To Run the Test:

1. Install Java and Maven
2. Import all the maven dependencies 
3. Place the correct version of chromedriver in the path src\main\resources\drivers\wdm\chromedriver
4. Run testngUAT.xml in the path src\test

* Log files are generated in logs folder
* Reports are generated in allure-results

* To run allure test report:
* Download the allure plugins to local drive
* Open command prompt and go to cd C:\allure-2.7.0\bin
* Execute the below command
allure serve C:\InterviewTestFramework\allure-results

