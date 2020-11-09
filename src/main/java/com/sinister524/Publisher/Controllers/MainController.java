package com.sinister524.Publisher.Controllers;

import com.sinister524.Publisher.Services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    MessageService messageService;

    @GetMapping
    public String getMessages (Model model) {
        logger.info("Sending GET request to SUBSCRIBER");
        model
                .addAttribute("msisdn", messageService.getMsisdn())
                .addAttribute("messages", messageService.getMessages());
        logger.info("Placement messages on the page");
        return "index";
    }

    @PostMapping
    public String doAct (@RequestParam String act) {
        logger.info("Sending POST request to SUBSCRIBER");
        if (act.equals("send")){
            messageService.sendMessage();
        } else if (act.equals("msisdn")) {
            messageService.generateNewMsisdn();
        }
        logger.info("Posting message");
        return "redirect:/";
    }
}
