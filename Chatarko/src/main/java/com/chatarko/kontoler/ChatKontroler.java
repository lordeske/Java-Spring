package com.chatarko.kontoler;


import com.chatarko.modeli.ChatPoruka;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class ChatKontroler {


    @MessageMapping("/chat.posaljiPoruku")
    @SendTo("/topic/public")
    public ChatPoruka posaljiPoruku(@Payload ChatPoruka chatPoruka)
    {

        return chatPoruka;
    }


    @MessageMapping("/chat.dodajKorisnika")
    @SendTo("/topic/public")
    public ChatPoruka dodajKorisnika(@Payload ChatPoruka chatPoruka,
                                     SimpMessageHeaderAccessor headerAccessor)
    {

        /// Omogucava da u Soket dodamo nas header novi, u nasem slucaju ime Posiljaoca
        headerAccessor.getSessionAttributes().put("Korisnik", chatPoruka.getPosiljalac());
        return chatPoruka;
    }


}
