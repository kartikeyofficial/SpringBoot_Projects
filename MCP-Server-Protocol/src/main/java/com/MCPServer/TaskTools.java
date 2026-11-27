package com.MCPServer;

import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;

import java.util.ArrayList;
import java.util.List;

public class TaskTools {

    private final List<String> tasks = new ArrayList<>();

    @McpTool(
            name = "create_task",
            description = "Create a New Task"
    )

    public String createTask(@McpToolParam(
            description = "Title of the task",
            required = true
    ) String title){
        tasks.add(title);
        return "Task created: "+title;

    }
    @McpTool(
            name = "list_task",
            description = "List of All Pending Task"
    )
    public List<String> listTask(){
        return List.copyOf(tasks);
    }

    @McpTool(
            name = "complete_task",
            description = "complete a Task Using its exact title"
    )
    public String completeTask(@McpToolParam(
            description = "Exact description of the Task",
            required = true
    ) String title){
        boolean removed = tasks.remove(title);
        if (removed){
            return  "Task Complete: "+title;
        }
        return "Task not found: "+title;

    }
}
