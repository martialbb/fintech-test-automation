# fintech-test-automation
This sample Web test automation verifies that a borrower is able to see loan offers , upon filling filling the required fom fields with valid input.
Instructions to run the web test automation
- Download the driver(s) for the browser(s) of your choice and add in root directory.
- This automation executes on chrome by default, you can also switch to firefox  or edge by updating the config file located at ./src/test/resources/config.properties

The ApiTest class currently has one test function that validates the status of the request and the product type.