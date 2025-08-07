package dev.jon.GeladeiraInteligente.repository;

import dev.jon.GeladeiraInteligente.model.FoodItemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItemModel, Long> {
}
