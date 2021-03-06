package selenium;// Generated by Selenium IDE

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;

public class MyTest1Test {
    private ChromeDriver driver;
    private Map<String, Object> vars;
    JavascriptExecutor js;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        vars = new HashMap<String, Object>();
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void myTest1() {
        driver.get("http://192.168.17.214:8003/iApp2/login.jsp");
        driver.findElement(By.id("j_username")).click();
        driver.findElement(By.id("j_username")).sendKeys("developer");
        driver.findElement(By.id("j_password")).click();
        driver.findElement(By.id("j_password")).sendKeys("1");
        driver.findElement(By.id("submit")).click();
        driver.findElement(By.id("item-more")).click();
        driver.findElement(By.cssSelector("#\\31 11033 > span")).click();
        driver.findElement(By.cssSelector("#\\31 11035 > .menu-two-item")).click();
        driver.switchTo().frame(1);
        driver.findElement(By.cssSelector(".ez-fl:nth-child(9) .icon-date")).click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(2);
        driver.findElement(By.id("dpTodayInput")).click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(1);
        driver.findElement(By.cssSelector("#btnQuery > .button-text")).click();
    }
}
