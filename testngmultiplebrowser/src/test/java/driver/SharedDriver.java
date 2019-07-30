package driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SharedDriver {

	public SharedDriver() {

		String browserType = "chrome";//System.getProperty("browser");
		String hubLink = "remoteIP/wd/hub";
		
		if (DriverFactory.getDriver() == null) {
			WebDriver driver = null;
			switch (browserType) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				break;
			case "remoteChrome":
				try {
					driver = new RemoteWebDriver(new URL(hubLink), getChromeCapabilities());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
                break;
			}
			driver.manage().window().maximize();
			DriverFactory.addDriver(driver);
		}
	}
	
	private DesiredCapabilities getChromeCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WIN8);

        return capabilities;
    }
}
