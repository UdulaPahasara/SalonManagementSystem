package Salon.SalonManagementSystem.controller;

import Salon.SalonManagementSystem.model.Product;
import Salon.SalonManagementSystem.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> all() {
        return service.getAll();
    }

    @PostMapping
    public Product create(@RequestBody Product p) {
        return service.save(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Integer id,
                          @RequestBody Product p) {
        return service.update(id, p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }


    // Get products filtered by Product Manager's assigned branches
    @GetMapping("/by-manager/{userId}")
    public List<Product> getByProductManager(@PathVariable Integer userId) {
        return service.getProductsByManager(userId);
    }



}
