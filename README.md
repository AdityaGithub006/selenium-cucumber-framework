# Selenium Cucumber Automation Framework

------------------------------------------------------------
Framework Features:
------------------------------------------------------------
1. Selenium WebDriver + Cucumber (BDD)
2. Page Object Model (POM) architecture
3. PicoContainer based Dependency Injection
4. Thread-safe WebDriver using ThreadLocal
5. Parallel execution using TestNG
6. Environment-based execution (qa / staging / prod)
7. Tag-based execution (@smoke, @regression, @negative)
8. Screenshot capture on scenario failure
9. Maven command-line execution support
10. CI-ready (GitHub Actions compatible)
------------------------------------------------------------

This framework is built to follow **enterprise-level automation standards**  
and is suitable for real-world projects and interview demonstrations.

------------------------------------------------------------
Atomic Test Design
------------------------------------------------------------
Each Cucumber scenario:
- Is independent
- Has its own browser session
- Does not depend on other scenarios

This ensures:
- Stability
- Parallel safety
- Easy debugging

------------------------------------------------------------
Project Structure
------------------------------------------------------------
src/test/java
- config        → Environment configuration reader
- data          → Test data reader
- driver        → DriverFactory (ThreadLocal WebDriver)
- factory       → UserFactory and object creation
- hooks         → Cucumber hooks (setup / teardown)
- model         → Domain models (User)
- pages         → Page Object classes
- runner        → TestNG Cucumber Runner
- steps         → Step Definitions

src/test/resources
- features      → Cucumber feature files
- config        → qa / staging / prod properties
- testdata      → users test data
------------------------------------------------------------

------------------------------------------------------------
Reports
------------------------------------------------------------
Generated after every execution:
- Cucumber HTML Report
- Cucumber JSON Report
- Cucumber JUnit XML Report

Location:
target/
------------------------------------------------------------

------------------------------------------------------------
Screenshot on Failure
------------------------------------------------------------
- Screenshot automatically captured on scenario failure
- Attached to Cucumber report
- Implemented using Cucumber Hooks
------------------------------------------------------------

------------------------------------------------------------
Parallel Execution
------------------------------------------------------------
- Scenario-level parallel execution
- Controlled via TestNG
- Thread-safe browser instances using ThreadLocal
------------------------------------------------------------

------------------------------------------------------------
Execution
------------------------------------------------------------
1. Default execution (qa environment)
mvn clean test

2. Execute with specific environment
mvn clean test -Denv=staging
mvn clean test -Denv=prod

3. Execute with tags
mvn clean test -Dcucumber.filter.tags="@smoke"
mvn clean test -Dcucumber.filter.tags="@regression"

4. Environment + tag execution
mvn clean test -Denv=qa -Dcucumber.filter.tags="@smoke"
------------------------------------------------------------

------------------------------------------------------------
CI / CD
------------------------------------------------------------
- Designed to run in CI environments
- Supports headless Chrome execution
- Compatible with GitHub Actions runners
------------------------------------------------------------

------------------------------------------------------------
Notes
------------------------------------------------------------
- Follows real-world automation practices
- Clean, maintainable, and scalable design
- Easily extensible for future enhancements
------------------------------------------------------------
