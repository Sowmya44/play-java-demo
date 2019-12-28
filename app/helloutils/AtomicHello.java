package helloutils;

import com.fasterxml.jackson.databind.JsonNode;
import play.mvc.Result;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class AtomicHello {
    private String msg = "Hello ";
    public String doSomethingFancy(){
        return msg;
    }
}
