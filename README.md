# HealthClinicETEbooking

Automated end-to-end testing for appointment booking on [katalon-demo-cura.herokuapp.com](https://katalon-demo-cura.herokuapp.com/), using **Selenium WebDriver**, **TestNG**, and **Microsoft Edge**.

---

##  Features

- Full automation of the CURA Healthcare appointment booking flow
- Login and booking form automated with:
  - Random selection of facility
  - Conditional checkbox selection
  - Random program (radio button) choice
  - Random future date generator
  - Randomized comment input
- Implemented using **TestNG** with ordered test execution
- Configured via `testng.xml` for batch execution
- Uses **WebDriverManager** for automatic Edge driver handling
- Runs on **Microsoft Edge** to avoid Google Chrome security popups

---

##  Technologies Used

- Java 17+
- Selenium WebDriver
- TestNG
- WebDriverManager (by Boni García)
- Microsoft Edge WebDriver
- Maven (optional for dependency management)
