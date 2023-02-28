package e2e;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;


public class TestBase {
    protected static AplicationManager app = new AplicationManager();   //Этот класс базовый , другие классы могут от него наследоваться *(в этом классе указаны аннотации кот мы можем использовать в др классах)


    public static Logger logger() {
        return LoggerFactory.getLogger(TestBase.class);
    }

    @BeforeMethod
    public void setupTest(Method m, Object[] p) {
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
            logger().info("FAILED" + result.getMethod().getMethodName());

        }
        logger().info("============================================================");
    }


}
