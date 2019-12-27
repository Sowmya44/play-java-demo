package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import utils.AtomicCounter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class CountController extends Controller {

    private AtomicCounter counter;

    @Inject
    public CountController(AtomicCounter counter){
        this.counter = counter;
    }

    public Result getCurrentCount(){
        return ok("Count: "+counter.getCount());
    }
}
