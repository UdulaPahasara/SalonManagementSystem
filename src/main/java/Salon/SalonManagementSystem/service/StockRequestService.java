package Salon.SalonManagementSystem.service;

import Salon.SalonManagementSystem.model.*;
import Salon.SalonManagementSystem.repository.StockRequestRepository;
import org.springframework.stereotype.Service;

@Service
public class StockRequestService {

    private final StockRequestRepository repo;

    public StockRequestService(StockRequestRepository repo) {
        this.repo = repo;
    }

    public StockRequest create(StockRequest request) {
        request.setStatus("PENDING");
        return repo.save(request);
    }

    public StockRequest approve(Integer id) {
        StockRequest r = repo.findById(id).orElseThrow();
        r.setStatus("APPROVED");
        return repo.save(r);
    }

    public StockRequest reject(Integer id) {
        StockRequest r = repo.findById(id).orElseThrow();
        r.setStatus("REJECTED");
        return repo.save(r);
    }
}
