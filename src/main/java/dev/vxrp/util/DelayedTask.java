package dev.vxrp.util;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedTask {
    public static void repeatTaskDelayed(Runnable runnable, int delay) {
        //Creating a single thread executor
        ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
        threadPool.scheduleWithFixedDelay(runnable, 0, delay, TimeUnit.SECONDS);
    }
}
