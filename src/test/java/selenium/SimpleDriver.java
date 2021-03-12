package selenium;

import com.google.common.collect.Lists;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * TODO
 * @author lgren
 * @since 2020-12-25 3:40 下午
 */
public class SimpleDriver {
    public static WebDriver getChromeDriver(boolean... isExclude) {
        if (isExclude.length > 0 && isExclude[0]) {
            // 创建webdriver驱动 规避检测
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
            return new ChromeDriver(options);
        }
        return new ChromeDriver();
    }
}
