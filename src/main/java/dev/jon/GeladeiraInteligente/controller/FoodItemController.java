package dev.jon.GeladeiraInteligente.controller;

import dev.jon.GeladeiraInteligente.model.FoodItemModel;
import dev.jon.GeladeiraInteligente.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService service;

    public FoodItemController(FoodItemService service) {
        this.service = service;
    }

    @PostMapping("/criar")
    public ResponseEntity<FoodItemModel> criar(@RequestBody FoodItemModel foodItem) {
        FoodItemModel itemCriado = service.criarItem(foodItem);
        return ResponseEntity.ok(itemCriado);
    }

    @GetMapping("/listar")
    public List<FoodItemModel> listar() {
       return service.listar();
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarItem (@PathVariable Long id, @RequestBody FoodItemModel itemAtualizado) {
        FoodItemModel itemAlterado = service.atualizarItem(id, itemAtualizado);
        if (itemAlterado != null) {
            return ResponseEntity.ok(itemAlterado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontrado");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarItem(@PathVariable Long id) {
        service.deletarItem(id);
        return ResponseEntity.ok("Item excluído com sucesso");
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> listarItensPorId(@PathVariable Long id) {
        FoodItemModel item =  service.listarItensPorId(id);
        if (item != null) {
            return ResponseEntity.ok(item);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }
    }


}
