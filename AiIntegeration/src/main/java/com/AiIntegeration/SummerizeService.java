package com.AiIntegeration;

import com.AiIntegeration.aitools.CalculatorTool;
import com.AiIntegeration.aitools.WeatherTool;
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
    private CalculatorTool calculatorTool;
    private WeatherTool weatherTool;
    private List<Message> history = new ArrayList<>();

    public SummerizeService(ChatClient.Builder builder,
                            CalculatorTool calculatorTool,
                            WeatherTool weatherTool)
    {
        this.chatClient = builder.build();
        this.calculatorTool = calculatorTool;
        this.weatherTool = weatherTool;

    }


    private final String SYSTEM_PROMPT = """
                You are a helpful AI assistant with access to external tools.
                follow these rules:
                
               1. For arithmetic calculations , Always Use calculator tool.
               2. Always use calculator tool for even trivial calculation.
               3. for current weather, Always use the currentWeather tool.
               4. for currency conversion or exchange rate, Always use the covertCurrencyTool.
               5. You may call multiply tool when solving a multi-step request.
               6. After receiving tool results, then explain the answer human language naturally.
               7. Never invent current weather or exchange-rate information.
                """;


    public String chat(String message){


        history.add(new UserMessage(message));
        String response = chatClient.prompt()
                .system(SYSTEM_PROMPT)
                .messages(history)
                .tools(calculatorTool,weatherTool)
                .call()
                .content();

        history.add(new AssistantMessage(response));

        return response;
    }
}

