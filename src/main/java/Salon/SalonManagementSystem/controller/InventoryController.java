package Salon.SalonManagementSystem.controller;

import Salon.SalonManagementSystem.model.BranchInventory;
import Salon.SalonManagementSystem.service.InventoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin
public class InventoryController {

    private final InventoryService service;

    public InventoryController(InventoryService service) {
        this.service = service;
    }

    @GetMapping("/product/{productId}")
    public List<BranchInventory> byProduct(@PathVariable Integer productId) {
        return service.getByProduct(productId);
    }
}
