package Salon.SalonManagementSystem.service;

import Salon.SalonManagementSystem.Dto.InventoryViewDTO;
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

    public List<InventoryViewDTO> getByProduct(Integer productId) {

        return repo.findByProduct_ProductId(productId)
                .stream()
                .map(i -> {
                    InventoryViewDTO dto = new InventoryViewDTO();
                    dto.setInventoryId(i.getInventoryId());
                    dto.setBranchId(i.getBranch().getId());
                    dto.setBranchName(i.getBranch().getBranchName());
                    dto.setQuantity(i.getQuantity());
                    return dto;
                })
                .toList();
    }
}
