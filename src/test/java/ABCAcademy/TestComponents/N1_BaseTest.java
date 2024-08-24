package ABCAcademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ABCAcademy.pageobject.N1_LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class N1_BaseTest {

	public WebDriver driver;
	public N1_LandingPage landingPage;
	public WebDriver initilizeDriver() throws IOException{
		//properties class
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//java//ABCAcademy//Resources//GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null	? System.getProperty("browser") : prop.getProperty("browser");
				
		if(browserName.contains("chrome")){
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));//full screen
		}
		else if((browserName.equalsIgnoreCase("firefox"))) {
			System.setProperty("webdriver.gecko.driver", "C:/Users/KhashiaTurRehman/Documents/Selenium/PrerequisiteSoftware/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else if((browserName.equalsIgnoreCase("edge"))) {
			//edge
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//driver.manage().window().maximize();
		return driver;
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		
		//reading JSON to String - send file as an argument
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		//Srting to Hashmap Jackson databind
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {
		//driver know that it has to take the screenshot now
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun=true)
	public N1_LandingPage launchApplication() throws IOException {
		driver = initilizeDriver();
		landingPage = new  N1_LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();

	}
	
}
