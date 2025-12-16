package Salon.SalonManagementSystem.service;

import Salon.SalonManagementSystem.model.Product;
import Salon.SalonManagementSystem.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public List<Product> getAll() {
        return repo.findAll();
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public Product update(Integer id, Product product) {
        product.setProductId(id);
        return repo.save(product);
    }

    public void delete(Integer id) {
        repo.deleteById(id);
    }
}
