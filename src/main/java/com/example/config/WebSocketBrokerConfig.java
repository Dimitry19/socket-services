package com.example.config;

import com.example.websocket.constants.Constants;
import com.example.websocket.filter.CrossDomainFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
@EnableWebSocket
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {



    @Bean
    public CrossDomainFilter corsFilters() throws Exception {
        return new CrossDomainFilter();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app"); //STOMP messages whose destination header begins with /app are routed to
                                                            //@MessageMapping methods in @Controller classes.
        registry.enableSimpleBroker("/notification", "/queue");// Use the built-in message broker for subscriptions and broadcasting and
                                                                                      //route messages whose destination header begins with /pmanager `or `/queue to the broker

       // registry.setPreservePublishOrder(true); // Order of Messages

    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
       // registry.addEndpoint(Constants.path)
         //       .setAllowedOrigins("*")
           //     .withSockJS(); //manager s the HTTP URL for the endpoint to which a WebSocket (or SockJS)
                                                                                    //client needs to connect for the WebSocket handshake.
           // .setStreamBytesLimit(512 * 1024) //Set the streamBytesLimit property to 512KB (the default is 128KB — 128 * 1024).
           // .setHttpMessageCacheSize(1000) //Set the httpMessageCacheSize property to 1,000 (the default is 100).
           // .setDisconnectDelay(30 * 1000); //Set the disconnectDelay property to 30 property seconds (the default is five seconds — 5 * 1000).

       registry.addEndpoint(Constants.path).setAllowedOrigins("*");
    }

  /*  @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.setInterceptors(new UserInterceptor());
    }*/



}
