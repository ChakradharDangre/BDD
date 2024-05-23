package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
		features = "src/test/resources/Features",
		glue = {"stepDef"},
		plugin = { "pretty" },
		monochrome = true,
		tags = "@Login"
		
		)
public class TestRunner extends AbstractTestNGCucumberTests{

}
