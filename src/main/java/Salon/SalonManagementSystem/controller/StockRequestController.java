package Salon.SalonManagementSystem.controller;

import Salon.SalonManagementSystem.model.StockRequest;
import Salon.SalonManagementSystem.service.StockRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stock-requests")
@CrossOrigin
public class StockRequestController {

    private final StockRequestService service;

    public StockRequestController(StockRequestService service) {
        this.service = service;
    }

    @PostMapping
    public StockRequest create(@RequestBody StockRequest request) {
        return service.create(request);
    }

    @PutMapping("/{id}/approve")
    public StockRequest approve(@PathVariable Integer id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    public StockRequest reject(@PathVariable Integer id) {
        return service.reject(id);
    }
}
