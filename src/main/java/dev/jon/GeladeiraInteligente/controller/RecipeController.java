package dev.jon.GeladeiraInteligente.controller;

import dev.jon.GeladeiraInteligente.service.ChatGptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receita")
public class RecipeController {

    private final ChatGptService chatGptService;

    public RecipeController(ChatGptService chatGptService) {
        this.chatGptService = chatGptService;
    }

    @GetMapping("/sugerir")
    public ResponseEntity<String> sugerirreceita() {
        String receita = chatGptService.sugerirReceita();
        return ResponseEntity.ok(receita);
    }
}