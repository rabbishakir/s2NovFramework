package testRunner;

import org.junit.runner.*;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features= {
				"src/test/resources/features/UserManagement.feature",
//				"src/test/resources/features/UserManagement.feature"
				},
		glue= {"stepDefinitions"},
		plugin = {"pretty","summary"},
		dryRun =false,
		monochrome=true
)

public class TestRunner {

}
