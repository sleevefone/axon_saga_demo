### 利用DynamicThresholdFilter定时任务日志级别

https://segmentfault.com/a/1190000021775922

```text
    <turboFilter class="ch.qos.logback.classic.turbo.DynamicThresholdFilter">
        <Key>mdc_key</Key>
        <!-- You can set default threshold as you like -->
        <DefaultThreshold>TRACE</DefaultThreshold>
        <MDCValueLevelPair>
            <value>hello</value>
            <level>ERROR</level>
        </MDCValueLevelPair>
    </turboFilter>
```
```text
import org.slf4j.*;
public class Quickie {
    static final Logger LOG = LoggerFactory.getLogger(Quickie.class);

    public static void main(String[] args) throws Exception {

        MyThread t1 = new MyThread("hi");
        MyThread t2 = new MyThread("bye");

        t1.start();
        t2.start();
    }
}


class MyThread extends Thread {
    static final Logger LOG = LoggerFactory.getLogger(MyThread.class);

    public MyThread(final String name) {
        this.setName(name);
    }

    public void run() {
        logSomething();
    }

    public void logSomething() {
        MDC.put("mdc_key", this.getName());
        LOG.info(getName());
        LOG.error(getName());
    }
}
```
