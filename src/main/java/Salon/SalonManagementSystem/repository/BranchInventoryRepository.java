package Salon.SalonManagementSystem.repository;

import Salon.SalonManagementSystem.model.BranchInventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchInventoryRepository extends JpaRepository<BranchInventory, Integer> {

    List<BranchInventory> findByProduct_ProductId(Integer productId);
}
