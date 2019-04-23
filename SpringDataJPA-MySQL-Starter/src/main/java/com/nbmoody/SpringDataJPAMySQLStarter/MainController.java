package com.nbmoody.SpringDataJPAMySQLStarter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.nbmoody.SpringDataJPAMySQLStarter.User;
import com.nbmoody.SpringDataJPAMySQLStarter.UserRepository;

// To test this running locally, try this (you'll need the DB running as specified under application.properties, and docker-compose.yml, also):
// curl 'localhost:8080/database/add?name=First&email=someemail@someemailprovider.com'
// curl 'localhost:8080/database/all'

@Controller
@RequestMapping(path="/database")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

//    @GetMapping("/one")
//    public @ResponseBody String getOneUser(@RequestParam String name) {
//        return userRepository.findByName(name).toString();
//
//    }


}
