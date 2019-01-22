package com.nbmoody.Springmvc;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;


@Controller
@RequestMapping("/controller")
public class ControllerExample {

    @ModelAttribute
    public void buildModel(Model model) {
        // Where does this Model object come from? The world may never know... Just trust the Spring..."
        String someText = "This is some text, stored in the Model object. It was transferred to the "
        + "view by Spring and utilized by Thymeleaf to generate the HTML for this page.";
        model.addAttribute("someText",someText);
        String someMoreText = "This is more text, also stored in the Model object. It was transferred to "
        + "the view by Spring and utilized by Thymeleaf to generate the HTML for this page.";
        model.addAttribute("someMoreText",someMoreText);
        List<String> thingsOnList = Arrays.asList("Foo", "Bar", "Monty", "Spam");
        model.addAttribute("thingsOnList",thingsOnList);
    }

    @GetMapping
    public String respondToGetRequest(Model model) {
        return "getResponse";
    }

    @PostMapping
    public String respondToPostRequest(Model model) {
        return "postResponse";
    }

}

