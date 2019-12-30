package com.example.BillingService.controller;

import com.example.BillingService.ESRegistry;
import com.example.BillingService.entity.User;
import com.example.BillingService.response.ActionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ScoreController {
    @Autowired
    private ESRegistry esRegistry;
    @GetMapping("/{id}")
    public Optional<User> simple(@PathVariable String id) {
        return esRegistry.findByUserId(id);
    }

    @PostMapping("/checkCredit/{id}/{credit}")
    public ActionResult<Boolean> checkCredit(@PathVariable String id , @PathVariable long credit){
        Optional<User> user = esRegistry.findByUserId(id);
        ActionResult<Boolean> result = new ActionResult<>();
        if(user.isPresent()){
            if(credit <= user.get().getCredit()){
                long new_credit = user.get().getCredit() - credit ;
                user.get().setCredit(new_credit);
                esRegistry.save(user.get());
                result.setSuccess(true);
            }else{
                result.setMessage("This id can not score this answer!");
            }
        }else{
            result.setMessage("This user does not exist");
        }
        return result ;
    }

//    @PostMapping("/json")
//    ActionResult<Boolean> jsonTest(@RequestBody User user){
//        System.out.println(user.getUserId());
//        System.out.println("user.getCredit() = " + user.getCredit());
//        return new ActionResult<>();
//    }
}
