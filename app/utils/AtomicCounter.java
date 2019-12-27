package utils;

import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton


public class AtomicCounter {

    private AtomicInteger counter = new AtomicInteger();

    public Integer getCount() {
        return counter.getAndIncrement();
    }
}
