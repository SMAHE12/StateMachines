package com.example.demo;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

@Configuration
@EnableStateMachine
public class StateMachineConfig
        extends EnumStateMachineConfigurerAdapter<States, Events> {

    @Override
    public void configure(StateMachineConfigurationConfigurer<States, Events> config)
            throws Exception {
        config
            .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }

    @Override
    public void configure(StateMachineStateConfigurer<States, Events> states)
            throws Exception {
        states
            .withStates()
                .initial(States.VALIDATE_USER)
                    .states(EnumSet.allOf(States.class));
        
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<States, Events> transitions)
            throws Exception {
        transitions
            .withExternal()
                .source(States.VALIDATE_USER).target(States.CHECK_INVENTORY).event(Events.USER_VALID).action(validateUserDetails())
                .and()
            .withExternal()
                .source(States.CHECK_INVENTORY).target(States.PREPARE_ORDER).event(Events.INVENTORY_AVAILABLE).action(checkInventory())
        .and()
        .withExternal()
            .source(States.PREPARE_ORDER).target(States.VALIDATE_PAYMENT).event(Events.VALID_PAYMENT).action(validatePayment());
    }

    @Bean
    public StateMachineListener<States, Events> listener() {
        return new StateMachineListenerAdapter<States, Events>() {
            @Override
            public void stateChanged(State<States, Events> from, State<States, Events> to) {
              System.out.println("<------ State changed from : "+from.getId() +" to  : " + to.getId() +"---->");
            }
        };
    }
    
    @Bean
    public Action<States, Events> validateUserDetails() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println("<=============== Started Business Steps in  the State  : "+context.getSource().getId() +"===============>" );

                System.out.println("----------------> Verify User First Name   : ");
                System.out.println("----------------> Verify User Pin code  : ");
                System.out.println("----------------> Verify User Last Name  : ");
                System.out.println("----------------> Contact 3rd party application and verify User Authenticity : ");
                System.out.println("<=============== All Business Steps  DONE   in the STATE: "+context.getSource().getId() +"===============>");
                System.out.println("");

            }
        };
    }
    
    @Bean
    public Action<States, Events> checkInventory() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println("<=============== Started Business Steps in  the State  : "+context.getSource().getId() +"===============>" );

                System.out.println("----------------> Check Item  1 Availability   : ");
                System.out.println("----------------> Check Item  2 Availability  : ");
                System.out.println("----------------> Check Item  3 Availability  : ");
                System.out.println("----------------> Check Item  1 Quantity  : ");
                System.out.println("----------------> Check Item  2 Quantity  : ");
                System.out.println("----------------> Check Item  3 Quantity  : ");
                System.out.println("----------------> Update Database with inventory details: ");
                System.out.println("----------------> Purge Cache with new Item details  : ");

                System.out.println("<=============== All Business Steps  DONE   in the STATE: "+context.getSource().getId() +"===============>");
                System.out.println("");

            }
        };
    }
    
    @Bean
    public Action<States, Events> prepareOrder() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println("<=============== Started Business Steps in  the State  : "+context.getSource().getId() +"===============>" );

                System.out.println("----------------> Reserve the Req Products  : ");
                System.out.println("----------------> Call the Store API to Get TOTE details : ");
                System.out.println("----------------> Update the Order details in the Cache  : ");
                System.out.println("----------------> Update the Cache details  : ");
                System.out.println("----------------> Trigger Event to EMF  : ");

                System.out.println("<=============== All Business Steps  DONE   in the STATE: "+context.getSource().getId() +"===============>");
                System.out.println("");

            }
        };
    }
    
    @Bean
    public Action<States, Events> validatePayment() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println("<=============== Started Business Steps in  the State  : "+context.getSource().getId() +"===============>" );

                System.out.println("----------------> Check Card Number  : ");
                System.out.println("----------------> Contact 3rd Party APi to Validae Balance  : ");
                System.out.println("----------------> Authorize the Payment  : ");
                System.out.println("----------------> Verify the Date  : ");
                System.out.println("<=============== All Business Steps  DONE   in the STATE: "+context.getSource().getId() +"===============>");
                System.out.println("");

            }
        };
    }
    
    @Bean
    public Action<States, Events> action() {
        return new Action<States, Events>() {

            @Override
            public void execute(StateContext<States, Events> context) {
                System.out.println("<=============== Started Business Steps in  the State  : "+context.getSource().getId() +"===============>" );

                System.out.println("----------------> Step 1 executed for the State  : ");
                System.out.println("----------------> Step 2 executed for the State  : ");
                System.out.println("----------------> Step 3 executed for the State  : ");
                System.out.println("----------------> Step 4 executed for the State  : ");
                System.out.println("<=============== All Business Steps  DONE   in the STATE: "+context.getSource().getId() +"===============>");
                System.out.println("");

            }
        };
    }
}