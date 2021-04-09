package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(
        features = {"src/test/java/features/loginPage.feature"},
        glue = "steps"
)
public class loginPageRunner extends AbstractTestNGCucumberTests{}
