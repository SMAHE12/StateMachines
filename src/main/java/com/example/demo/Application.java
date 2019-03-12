package com.example.demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.statemachine.StateMachine;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StateMachine<States, Events> stateMachine;

	@Override
	public void run(String... args) throws Exception {
	    stateMachine.sendEvent(Events.USER_VALID);
	    stateMachine.sendEvent(Events.INVENTORY_AVAILABLE);
	    stateMachine.sendEvent(Events.VALID_PAYMENT);
	    
	}
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}