package com.chatarko.config;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@RequiredArgsConstructor
@Component
@Slf4j
public class VebSoketEventOsluskivac {

    @EventListener
    public void handleVebSoketDiskonekt(SessionDisconnectEvent dogadjaj)
    {
        /// Dodati posle
    }




}
