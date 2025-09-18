package objectRepository;

import org.openqa.selenium.By;

public class HomePageObjects {
    // Sign In
    public static final By signInBtn = By.xpath("//*[@id='container']/div[1]/header/div/div/div[4]/div[2]");

    // Image link
    public static final By imageLink = By.xpath("//*[@id='slick-slide00']/div/div[3]/a[1]/img");

    // Blog link
    public static final By blogLink = By.xpath("//*[@id='container']/div[3]/div/div[1]/div[1]/div[2]/div[2]/span[5]/a");

    // Example Input field
    public static final By pincodeField = By.xpath("//*[@id='content']/div/div[1]/div[8]/div/div/div[2]/form/input[3]");
}