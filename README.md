# Appium+java+cucumber framework for mobile test automation #

This framework purpose is the implementation and execution of acceptance tests for android and ios mobile application. It consists of predefined dependencies and API's that will help to describe the expected behaviour of mobile app.

## Execution ##
### Preconditions: ###
* Run Appium server - "appium"
* Check for the correct data in your config file - *.properties. Appium server url should be you appium node instance host.

#### ANDROID ####
* Before executing tests you need to create an android emulator which will be used as a testing device or connect a real physical device to you machine. With existing config it should be an Android emulator with OS version 8.0
* Run the Android emulator you just created  - "emulator -avd <emulator_name>"

#### IOS ####
* Before executing tests you need download Xcode from appstore and check that simulators with correct ios version are installed. With existing config it should be an iPhone 7 simulator with iOS version 10.3.1
* Config for ios execution (dev.ios.properties) should contain correct udid of the simulator you are using (device.udid). You can find it in Xcode "Devices and Simulators" window as "Identifier" property for your device.

### Run: ###
* To execute a test case run "mvn clean verify" command. 
* Specify tags to run in TestRunner file or through terminal with -Dcucumber.options="--tags @smoke"
* Specify platform and env with system variable -Denv (ex. -Denv=dev.ios)
* Example command to run smoke test on ios simulator - "mvn clean verify -Dcucumber.options"--tags @smoke" -Denv=dev.ios"
* Results are stored in html format in "output/report.html" directory and cucumber report is in "target/reports.cucumber.json"
