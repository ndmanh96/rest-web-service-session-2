package com.manhcode.rest.demo.international;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InternationalController {
    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hello")
    public String sayHello() {
        return messageSource.getMessage("label.hello", null,
                LocaleContextHolder.getLocale());
    }
}
