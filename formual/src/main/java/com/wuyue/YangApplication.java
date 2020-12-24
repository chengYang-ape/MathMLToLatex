package com.wuyue;

import com.wuyue.until.MathmlUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;



@SpringBootApplication
public class YangApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(YangApplication.class);
    }

    /*public static void main(String[] args) {
        SpringApplication.run(YangApplication.class, args);
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","{}");
    }*/
    public static void main(String[] args) throws Exception {



    }

}
