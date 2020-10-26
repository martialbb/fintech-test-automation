# fintech-test-automation
This sample Web test automation verifies that a borrower is able to see loan offers , upon filling filling the required fom fields with valid input.
Instructions to run the web test automation
- Download the driver(s) for the browser(s) of your choice and add them in root directory.
- This automation executes on chrome by default, you can also switch to firefox  or edge by updating the config file located at ./src/test/resources/config.properties

The ApiTest class currently has one test function that validates the status of the request, and the product type.
There is also a negative test scenario that an invalid login.

- In your terminal, execute "mvn clean test" from the root directory to run both Web and API tests.
You can also execute these test from your IDE clicking on the run button.