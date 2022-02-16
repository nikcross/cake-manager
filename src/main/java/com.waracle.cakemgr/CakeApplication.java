package com.waracle.cakemgr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@CrossOrigin( value = "*" )
public class CakeApplication {

    private static class Version {
        public String text = "v1.0";
    }
    private static Version VERSION = new Version();

    public static void main(String[] args) {

        try {

            SpringApplication.run(CakeApplication.class, args);

        } catch(Throwable e) {
            System.out.println( e );
        }
    }

    @GetMapping(value = "/version")
    public Version getVersion() {
        return VERSION;
    }

    @GetMapping("/")
    public String getRoot() {
        return "Cake Manager " + VERSION.text;
    }
}
