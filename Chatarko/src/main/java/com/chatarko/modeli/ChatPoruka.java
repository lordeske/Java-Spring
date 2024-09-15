package com.chatarko.modeli;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatPoruka {


    private String tijeloPoruke;
    private String posiljalac;

    //Saljemo teskt u zavisnoti sta je tip poruke
    private tipPoruke tipPoruke;

}
