package kr.co.ffm.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class AgentFfmProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentFfmProjectApplication.class, args);
    }

}
