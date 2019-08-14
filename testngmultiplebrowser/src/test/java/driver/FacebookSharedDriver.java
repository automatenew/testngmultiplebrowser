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

public class FacebookSharedDriver {

	public FacebookSharedDriver() {

		String browserType = "chrome";//System.getProperty("browser");
		String hubLink = "remoteIP/wd/hub";
		
		if (FacebookDriverFactory.getFacebookDriver() == null) {
			WebDriver gmailDriver = null;
			switch (browserType) {
			case "chrome":
				WebDriverManager.chromedriver().setup();
				gmailDriver = new ChromeDriver();
				break;
			case "firefox":
				WebDriverManager.firefoxdriver().setup();
				gmailDriver = new FirefoxDriver();
				break;
			case "remoteChrome":
				try {
					gmailDriver = new RemoteWebDriver(new URL(hubLink), getChromeCapabilities());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
                break;
			}
			gmailDriver.manage().window().maximize();
			FacebookDriverFactory.addFacebookDriver(gmailDriver);
			System.out.println("FacebookSharedDriver()---->Thread ID = " + Thread.currentThread().getId());
			System.out.println("FacebookSharedDriver()---->Thread NAME = "+Thread.currentThread().getName());
			
		}
	}
	
	private DesiredCapabilities getChromeCapabilities() {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(Platform.WIN8);

        return capabilities;
    }
}
