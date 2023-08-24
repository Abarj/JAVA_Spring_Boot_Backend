package com.example.block25SpringCloudInventoryService.infrastructure.repository;

import com.example.block25SpringCloudInventoryService.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
