package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import helloutils.AtomicHello;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class HelloController extends Controller {
    Map<Integer,String> map = new HashMap<>();
    private AtomicHello atomicHello;
    @Inject
    public HelloController(AtomicHello atomicHello){
        this.atomicHello = atomicHello;
    }
    public Result doSomething() {
        return ok(atomicHello.doSomethingFancy());

    }

    public Result helloUser(String uname) {
        String msg = atomicHello.doSomethingFancy() + uname;
        return ok(msg);
    }

    public Result helloUserWithDetails() {
        JsonNode requestJson = request().body().asJson();
        String firstName = null;
        String lastName = null;
        if (requestJson.has("first_name")) {
            firstName = requestJson.get("first_name").asText();
        }
        if (requestJson.has("last_name")) {
            lastName = requestJson.get("last_name").asText();
        }
        if (firstName != null && lastName != null) {
            String msg = "hello " + firstName + " " + lastName;
            return ok(msg);
        }
        return badRequest("Provide both first_name and last_name");
    }
    public Result helloUserMap(){
        JsonNode requestJson = request().body().asJson();
        String userName = null;
        int userId = 0;
        String msg = null;


        if (requestJson.has("user_name")) {
            userName = requestJson.get("user_name").asText();
        }
        if (requestJson.has("user_id")) {
            userId = requestJson.get("user_id").asInt();
        }
        if (userName != null && userId != 0) {
            map.put(userId , userName);

            for (Integer id: map.keySet()){
                String key = id.toString();
                String value = map.get(id).toString();
                msg = "Your name is "+value+" and your id is "+key;
                return ok(msg);
            }

        }
        return badRequest("Provide user_name and user_id");
    }

    public Result helloUserMapDetails(int userId){
        String msg = null;
        for (Integer id: map.keySet()) {
            if (id == userId) {
                String key = id.toString();
                String value = map.get(id).toString();
                msg = "Your name is " + value + " and your id is " + key;
                return ok(msg);
            }
        }
        return badRequest("UserId doesn't exist");
    }
}
