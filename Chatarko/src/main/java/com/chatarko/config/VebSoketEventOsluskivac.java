package com.chatarko.config;


import com.chatarko.modeli.ChatPoruka;
import com.chatarko.modeli.tipPoruke;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@RequiredArgsConstructor
@Component
@Slf4j
public class VebSoketEventOsluskivac {


    private final SimpMessageSendingOperations templatePoruke;


    @EventListener
    public void handleVebSoketDiskonekt(SessionDisconnectEvent dogadjaj)
    {
        StompHeaderAccessor headerAccessor  = StompHeaderAccessor.wrap(dogadjaj.getMessage());
        String korisnickoIme = (String) headerAccessor.getSessionAttributes().get("Korisnik");


        if(korisnickoIme != null)
        {
            log.info("Korisnik se diskonektovao: {}", korisnickoIme);
            var chatPoruka = ChatPoruka.builder()
                    .tipPoruke(tipPoruke.IZLAZ)
                    .posiljalac(korisnickoIme)
                    .build();


            /// mjesto gdje saljemo poruku
            templatePoruke.convertAndSend("/topic/public", chatPoruka);
        }

    }




}
