package br.com.lm.shop.config;

import java.util.EnumSet;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachine;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineConfigurationConfigurer;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;
import org.springframework.statemachine.listener.StateMachineListener;
import org.springframework.statemachine.listener.StateMachineListenerAdapter;
import org.springframework.statemachine.state.State;

import br.com.lm.shop.entities.OrderEvents;
import br.com.lm.shop.entities.OrderStates;

@Configuration
@EnableStateMachine
public class SimpleStateMachineConfiguration extends StateMachineConfigurerAdapter<OrderStates, OrderEvents> {
	
	@Override
    public void configure(StateMachineConfigurationConfigurer<OrderStates, OrderEvents> config)
            throws Exception {
        config
            .withConfiguration()
                .autoStartup(true)
                .listener(listener());
    }
 
    @Override
    public void configure(StateMachineStateConfigurer<OrderStates, OrderEvents> states) 
      throws Exception {
  
        states
          .withStates()
          .initial(OrderStates.CART)
          .choice(OrderStates.CHECKING_STOCK)
          .choice(OrderStates.CHECKING_PAYMENT)
          .end(OrderStates.CANCELLED)
          .end(OrderStates.DELIVERED)
          .states(EnumSet.allOf(OrderStates.class));
 
    }
 
    @Override
    public void configure(
      StateMachineTransitionConfigurer<OrderStates, OrderEvents> transitions) 
      throws Exception {
  
        transitions
        	.withExternal()
        		.source(OrderStates.CART)
        			.target(OrderStates.ORDERED)
        				.event(OrderEvents.CHECKOUT).and()
          .withExternal()
          		.source(OrderStates.ORDERED)
          			.target(OrderStates.ACCEPTED)
          				.event("E2").and()
          .withExternal()
          .source("S2").target("SF").event("end");
    }
    
    @Bean
    public StateMachineListener<OrderStates, OrderEvents> listener() {
        return new StateMachineListenerAdapter<OrderStates, OrderEvents>() {
            @Override
            public void stateChanged(State<OrderStates, OrderEvents> from, State<OrderStates, OrderEvents> to) {
                System.out.println("State change to " + to.getId());
            }
        };
    }
}