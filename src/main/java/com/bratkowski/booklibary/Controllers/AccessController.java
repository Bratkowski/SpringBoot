package com.bratkowski.booklibary.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccessController {

    @RequestMapping(value = "/acces-denied", method = RequestMethod.GET)
    public String accesDenied () {
        return "acces-denied";
    }
}
