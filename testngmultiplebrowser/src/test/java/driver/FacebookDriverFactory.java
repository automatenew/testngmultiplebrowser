package driver;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public final class FacebookDriverFactory {

	private static ThreadLocal<WebDriver> facebookdrivers = new ThreadLocal<>();

	// To quit the drivers and browsers at the end only.
	private static List<WebDriver> storedFacebookDrivers = new ArrayList<>();

	static {
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				storedFacebookDrivers.stream().forEach(WebDriver::quit);
			}
		});
	}

	private FacebookDriverFactory() {}

	public static WebDriver getFacebookDriver() {
		return facebookdrivers.get();
	}

	public static void addFacebookDriver(WebDriver driver) {
		storedFacebookDrivers.add(driver);
		facebookdrivers.set(driver);
		System.out.println("FacebookDriverFactory--->addFacebookDriver--Thread ID = " + Thread.currentThread().getId());
		System.out.println("FacebookDriverFactory---->addFacebookDriver--Thread NAME = "+Thread.currentThread().getName());
	}

	public static void removeDriver() {
		storedFacebookDrivers.remove(facebookdrivers.get());
		facebookdrivers.remove();
		System.out.println("FacebookDriverFactory--->Thread ID = " + Thread.currentThread().getId());
		System.out.println("FacebookDriverFactory---->Thread NAME = "+Thread.currentThread().getName());
	}
}
