package dev.jon.GeladeiraInteligente.service;

import dev.jon.GeladeiraInteligente.model.FoodItemModel;
import dev.jon.GeladeiraInteligente.repository.FoodItemRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final FoodItemRepository foodItemRepository;
    private final ChatClient chatClient;

    public ChatGptService(FoodItemRepository foodItemRepository, ChatClient.Builder chatClientBuilder) {
        this.foodItemRepository = foodItemRepository;
        this.chatClient = chatClientBuilder.build();
    }

    public String sugerirReceita() {
        List<FoodItemModel> ingredientes = foodItemRepository.findAll();

        if (ingredientes.isEmpty()) {
            return "Não há ingredientes cadastrados para sugerir uma receita.";
        }
        String listaIngredientes = ingredientes.stream()
                .map(FoodItemModel::getNome)
                .collect(Collectors.joining(", "));

        String promptText = """
                Você é um chef de cozinha brasileiro experiente e criativo.
                Sua tarefa é criar uma receita deliciosa usando apenas os ingredientes disponíveis.
                Os ingredientes são: {ingredientes}.
                Se os ingredientes não fizerem sentido juntos para uma receita, seja honesto e, em vez de criar algo ruim, sugira adicionar itens básicos para tornar a receita viável.
                A resposta deve ser formatada em Markdown, seguindo exatamente esta estrutura:
                **Nome da Receita:** (um nome criativo)
                **Ingredientes Utilizados:** (liste os ingredientes)
                **Modo de Preparo:** (passos numerados)
                **Dica do Chef:** (uma dica opcional para melhorar o prato)
                """;

        PromptTemplate promptTemplate = new PromptTemplate(promptText);

        var promptFinal = promptTemplate.create(Map.of("ingredientes", listaIngredientes));

        return chatClient.prompt(promptFinal)
                .call()
                .content();
    }
}