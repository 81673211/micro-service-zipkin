package com.fred.springcloud.zipkin;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import brave.sampler.Sampler;

/**
 *
 * <b>Description:.</b><br> 
 * @author <b>sil.zhou</b>
 * <br><b>ClassName:</b> 
 * <br><b>Date:</b> 2019/4/15 14:02
 */
@SpringBootApplication
@RestController
public class ServiceMiyaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceMiyaApplication.class, args);
    }

    private static final Logger LOGGER = Logger.getLogger(ServiceMiyaApplication.class.getName());

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/miya")
    public String info() {
        LOGGER.log(Level.INFO, "info is being called");
        return restTemplate.getForObject("http://localhost:8881/info", String.class);
    }

    @RequestMapping("/hi")
    public String home(){
        LOGGER.log(Level.INFO, "hi is being called");
        return "hi i'm miya!";
    }
}
