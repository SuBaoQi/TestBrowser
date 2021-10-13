package com.coco.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @author Bob
 * @create 2021-03-09 10:36
 */
public class BaiduTest
{
    public static void main(String[] args) {
        WebDriver driver = openBrowser("chrome");
        driver.get("https://www.baidu.com");
        //使你的浏览器窗口最大化
        driver.manage().window().maximize();
        //使你的浏览器窗口全屏
        //driver.manage().window().fullscreen();
        //ChromeDriver \ FirefoxDriver\ InternetExplorerDriver继承于WebDriver -- 多态
    }

    /**
     * 封装统一的去打开浏览器方法
     * @param browserName 浏览器名字
     * @return 对应的驱动
     */
    public static WebDriver openBrowser(String browserName) {
        if (browserName.equals("chrome")) {
            // 一、打开chrome V68版本
            // 1、打开测试浏览器-chrome
            // The path to the driver executable must be set by the
            // webdriver.chrome.driver system property
            // 设置系统属性，属性名webdriver.chrome.driver 属性值：chromeDriver驱动所在路径
                            // 相当于环境变量，让测试脚本认识到chromeDriver驱动在哪里
            System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
            ChromeDriver chromeDriver = new ChromeDriver();
            return chromeDriver;

        } else if (browserName.equals("firefox")) {
            // 二、打开Firefox V56
            // Cannot find firefox binary in PATH. Make sure firefox is
            // installed
            // 设置系统属性，属性名：webdriver.firefox.bin，属性值：firefox启动的路径 --
            // 告诉脚本firefox的启动文件在哪里
            System.setProperty("webdriver.firefox.bin", "D:\\huohu\\firefox.exe");
            // The path to the driver executable must be set by the
            // webdriver.gecko.driver system property
            // 设置系统属性，属性名：webdriver.gecko.driver ,属性值：firefox驱动对应的路径
            System.setProperty("webdriver.gecko.driver", "driver/geckodriver.exe");
            FirefoxDriver firefoxDriver = new FirefoxDriver();
            return firefoxDriver;

        } else if (browserName.equals("ie")) {

            // 三、打开IE
            // The path to the driver executable must be set by the
            // webdriver.ie.driver system property
            System.setProperty("webdriver.ie.driver", "driver/IEDriverServer32.exe");
            // Unexpected error launching Internet Explorer. Protected Mode
            // settings
            // are not the same for all zones
            // 解决方案：
            // 1、手动方式，关闭IE的保护模式，打开IE的设置->保护->所有选项的保护模式全部关闭 -- 不推荐
            // 2、通过代码的方式设置
            // 取消IE安全设置（忽略IE的Protected Mode的设置）
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            // Unexpected error launching Internet Explorer. Browser zoom level
            // was
            // set to 125%. It should be set to 100%
            // 解决方案：
            // 1、手动方式，设置浏览器缩放百分比为100%
            // 2、通过代码的方式设置
            capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            InternetExplorerDriver ieDriver = new InternetExplorerDriver(capabilities);
            return ieDriver;
        }
        return null;
    }
}
