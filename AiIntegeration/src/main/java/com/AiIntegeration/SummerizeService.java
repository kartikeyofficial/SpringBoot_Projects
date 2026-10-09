package com.AiIntegeration;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SummerizeService {

    private ChatClient chatClient;
    private List<Message> history = new ArrayList<>();

    private final String SYSTEM_PROMPT = """
                You Are a Customer Support executive of our food delivery application called Tomato. 
                Respond to Our Customers query professionally.
                If user is furious, or angry or have any issue use words like I Understand your Concern, 
                or I am Sorry you have to through this and so on. 
                Then solve customer query and give a response. 
                
                Always response not more than 2 lines.  
                
                Do not Response to any other message which is not related to ordering Food query, 
                refund query, order tracking status query status query or company policy query.
                If below message ask an other information other then food 
                delivery just respond this is beyond my capabilities
                """;

    public SummerizeService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String chat(String message){


        history.add(new UserMessage(message));
        String output = chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .messages(history)
                .call()
                .content();

        history.add(new AssistantMessage(output));

        return output;
    }
}

