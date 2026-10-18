package com.AiIntegeration.aitools;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class CalculatorTool {
    @Tool(description = """
            Perform Arithmetic calculations.
            Supported operation: add, subtract , multiply, divide, mod, power.
            """)
    private double calculator(
            @ToolParam(description = "Operations: add, subtract, multiply, divide, power")
            String operation,
            @ToolParam(description = "First Number")
            double a,
            @ToolParam(description = "Second Number")
            double b){
        System.out.println("Calculator Tool called");

        if (operation.equals("add")){
            return  a+b;
        }
        else if (operation.equals("subtract")){
            return a-b;
        }
        else if (operation.equals("divide")) {
            if (b==0){
                throw new IllegalArgumentException("Can not divide by 0");
            }
            return a/b;

        } else if (operation.equals("multiply")) {
            return a*b;

        } else if (operation.equals("mod")) {
            if (b==0){
                throw new IllegalArgumentException("Cannot calculate mod by 0");
            }

        } else if (operation.equals("power")) {
            return Math.pow(a,b);
            
        } else {
            throw new IllegalArgumentException("Unsupported operation "+operation);
            
        }
        return 0;
    }
}
