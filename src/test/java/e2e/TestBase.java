package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.Arrays;


public class TestBase {
    protected static AplicationManager app = new AplicationManager();   //Этот класс базовый , другие классы могут от него наследоваться *(в этом классе указаны аннотации кот мы можем использовать в др классах)


    public static Logger logger() {
        return LoggerFactory.getLogger(TestBase.class);
    }

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();                      //Здесь можно указывать любой вид драйвера
        //будет показываться лог с методом

    }

    @BeforeMethod
    public void setupTest(Method m, Object[] p) throws MalformedURLException {
        app.init();
    }

    @BeforeMethod
    public void startTest(Method m, Object[] p) {
        logger().info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
    }


    //after
    @AfterMethod
    public void tearDown() {
        app.stop();

    }

    @AfterMethod
    public void stopTest(ITestResult result) throws IOException {
        if (result.isSuccess()) {
            logger().info("PASSED" + result.getMethod().getMethodName());
        } else {
            logger().info("FAILED" + result.getMethod().getMethodName() + "Screenshot path: " + app.takeScreenshot());

        }
        logger().info("============================================================");
    }

}
