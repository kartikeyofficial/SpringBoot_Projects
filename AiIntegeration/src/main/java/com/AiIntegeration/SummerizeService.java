package com.AiIntegeration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class SummerizeService {

    private ChatClient chatClient;

    public SummerizeService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String summerize(String ticket){
        String output = chatClient.prompt()
                .user("Summerize this Support Ticket in two Lines: \n\n"+ticket)
                .call().content();


        return output;
    }
}
