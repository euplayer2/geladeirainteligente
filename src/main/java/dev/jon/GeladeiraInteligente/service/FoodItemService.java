package dev.jon.GeladeiraInteligente.service;

import dev.jon.GeladeiraInteligente.model.FoodItemModel;
import dev.jon.GeladeiraInteligente.repository.FoodItemRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.List;

@Service
public class FoodItemService {

    private FoodItemRepository repository;

    public FoodItemService(FoodItemRepository repository) {
        this.repository = repository;
    }

    public FoodItemModel criarItem (FoodItemModel foodItem) {
        return repository.save(foodItem);
    }

    public List<FoodItemModel> listar(){
        return repository.findAll();
    }

    public FoodItemModel listarItensPorId(Long id) {
        Optional <FoodItemModel> itemPorId = repository.findById(id);
        return itemPorId.orElse(null);
    }

    public FoodItemModel atualizarItem(Long id, FoodItemModel itemAtualizado) {
        Optional<FoodItemModel> itemExiste = repository.findById(id);
        if (itemExiste.isPresent()) {
            itemAtualizado.setId(id);
            FoodItemModel itemSalvo = repository.save(itemAtualizado);
            return itemSalvo;
        }
        return null;
    }

    public void deletarItem(Long id) {
        repository.deleteById(id);
    }








}
