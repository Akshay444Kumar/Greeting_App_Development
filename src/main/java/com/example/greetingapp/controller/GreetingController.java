package com.example.greetingapp.controller;

import com.example.greetingapp.model.Greeting;
import com.example.greetingapp.service.IGreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Autowired
    private IGreetingService greetingService;
    private final static String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting((int) counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/hey")
    public String sayHello(){
        return greetingService.sayHello();
    }

    @GetMapping("/ByName")
    public String greetingWithUserName(@RequestParam(value = "firstName", defaultValue = "") String firstName,
                                       @RequestParam(value = "lastName", defaultValue = "") String lastName) {
        return greetingService.greetingWithName(firstName, lastName);
    }

    @PostMapping("/addGreeting")
    public Greeting addGreeting(@RequestBody Greeting greeting){
        return greetingService.saveGreetings(greeting);
    }

    @GetMapping("/findById/{id}")
    public Greeting getGreetingById(@PathVariable Integer id){
        return greetingService.findGreetingById(id);
    }

    @GetMapping("/allGreetings")
    public List<Greeting> findAllGreetings(){
        return greetingService.findGreetings();
    }

    @PutMapping("/editGreeting/{id}")
    public Greeting editGreeting(@RequestBody Greeting greeting, @PathVariable Integer id){
        return greetingService.editGreeting(greeting, id);
    }

    @DeleteMapping("/deleteGreeting/{id}")
    public String deleteGreeting(@PathVariable Integer id){
        greetingService.deleteGreeting(id);
        return "Data Deleted Successfully";
    }
}
