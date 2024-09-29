package com.example.application.client;


import com.example.application.services.CustomerSupportAssistant;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import com.vaadin.hilla.BrowserCallable;

import reactor.core.publisher.Flux;

@BrowserCallable
@AnonymousAllowed
public class AssistantService {

    private final CustomerSupportAssistant agent;

    public AssistantService(CustomerSupportAssistant agent) {
        this.agent = agent;
    }

    public Flux<String> chat(String chatId, String userMessage) {
        return agent.chat(chatId, userMessage);
    }
}