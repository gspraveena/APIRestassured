package apitesting;

 
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(

		features = "/Users/praveenag/eclipse-workspace/API_Bootcamp/src/test/resources/Features/user.feature",
		glue = "apitesting", 
		plugin = {
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "pretty",
				"html:target/cucumber-reports/cucumber.html",
				 },
		dryRun=false,
		monochrome = true
		 
)

public class testRunn {
	}
