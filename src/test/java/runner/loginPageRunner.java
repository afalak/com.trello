package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = "src/test/java/features", glue = "steps", plugin = {
    "pretty" })
public class loginPageRunner extends AbstractTestNGCucumberTests {

}
