package com.switchfully.vaadin.ordergui.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.switchfully.vaadin.ordergui.webapp",
        "com.switchfully.vaadin.ordergui.interfaces"
})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }

}