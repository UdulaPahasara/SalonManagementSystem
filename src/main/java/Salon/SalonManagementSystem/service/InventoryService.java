package Salon.SalonManagementSystem.service;

import Salon.SalonManagementSystem.model.BranchInventory;
import Salon.SalonManagementSystem.repository.BranchInventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    private final BranchInventoryRepository repo;

    public InventoryService(BranchInventoryRepository repo) {
        this.repo = repo;
    }

    public List<BranchInventory> getByProduct(Integer productId) {
        return repo.findByProduct_ProductId(productId);
    }
}
