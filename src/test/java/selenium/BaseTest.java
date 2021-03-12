package selenium;

import com.google.common.collect.Lists;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.HttpCookie;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * TODO
 * @author lgren
 * @since 2020-10-29 4:18 下午
 */
public class BaseTest {
    private List<Set<Cookie>> cookiesList = new ArrayList<>();
    @Test
    public void test1() throws InterruptedException {
        // 1.创建webdriver驱动 规避检测
        ChromeOptions options = new ChromeOptions().setExperimentalOption("excludeSwitches", Lists.newArrayList("enable-automation"));
        // // 禁用阻止弹出窗口
        // options.addArguments("--disable-popup-blocking");
        // // 启动无沙盒模式运行
        // options.addArguments("no-sandbox");
        // // 禁用扩展
        // options.addArguments("disable-extensions");
        // // 默认浏览器检查
        // options.addArguments("no-default-browser-check");
        // Map<String, Object> prefs = new HashMap<>();
        // prefs.put("credentials_enable_service", false);
        // prefs.put("profile.password_manager_enabled", false);
        // // 禁用保存密码提示框
        // options.setExperimentalOption("prefs", prefs);
        // // 默认浏览器检查
        WebDriver driver = new ChromeDriver(options);
        driver.manage().getCookies();
        // 2.打开百度首页
        driver.get("http://cdhrss.chengdu.gov.cn/es-search/search/e444eaeca74c4be6a186e84834d16a7b?_template=zhaofa/cdsrs_list&_isAgg=1&_pageSize=100&page=1");
        cookiesList.add(driver.manage().getCookies());
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // TimeUnit.SECONDS.sleep(4);
        // cookiesList.add(driver.manage().getCookies());
        // HttpCookie.parse(cookiesList.get(0).stream().map(c -> c.getName() + "=" + c.getValue()).collect(Collectors.joining(";")))
        System.out.println();

        // // 3.获取输入框，输入selenium
        // driver.findElement(By.id("kw")).sendKeys("selenium");
        // // 4.获取“百度一下”按钮，进行搜索
        // driver.findElement(By.id("su")).click();
        // // 5.退出浏览器
        // driver.quit();

    }
}
