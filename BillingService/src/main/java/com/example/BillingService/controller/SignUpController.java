package com.example.BillingService.controller;

import com.example.BillingService.ESRegistry;
import com.example.BillingService.entity.User;
import com.example.BillingService.response.ActionResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class SignUpController {

    private ESRegistry esRegistry ;

    SignUpController(ESRegistry esRegistry){
        this.esRegistry = esRegistry;
    }

    @PostMapping("/signup")
    public ActionResult<Boolean> signUp(@RequestParam String id){
        ActionResult<Boolean> result = new ActionResult<>();
        Optional<User> check = esRegistry.findByUserId(id);
//        System.out.println(check.isPresent());
        if (!check.isPresent()) {
            User user = new User(id, 10);
            esRegistry.save(user);
            result.setSuccess(true);
        }else{
            result.setMessage("This id is already exists!");
        }
        return result;
    }
}
