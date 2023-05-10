package com.tms;

import com.tms.annotation.GetTimeAnnotation;
import org.springframework.stereotype.Component;

@Component
public class SomeLogic {
@GetTimeAnnotation
    public void firsttestMethod(String name) {
        System.out.println("HELLO!1");
        System.out.println("HELLO!2");
        System.out.println("HELLO!3");

    }

    String secondtestMethod() {
        System.out.println("BUY!1");
        System.out.println("BUY!2");
        System.out.println("BUY!3");
        return "123";
    }
}
