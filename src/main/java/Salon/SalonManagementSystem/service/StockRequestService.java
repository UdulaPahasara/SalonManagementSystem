package Salon.SalonManagementSystem.service;

import Salon.SalonManagementSystem.Dto.StockRequestCreateDTO;
import Salon.SalonManagementSystem.model.*;
import Salon.SalonManagementSystem.repository.BranchRepository;
import Salon.SalonManagementSystem.repository.ProductRepository;
import Salon.SalonManagementSystem.repository.StockRequestRepository;
import Salon.SalonManagementSystem.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class StockRequestService {

    private final StockRequestRepository repo;
    private final ProductRepository productRepo;
    private final BranchRepository branchRepo;
    private final UsersRepository usersRepo;

    public StockRequestService(
            StockRequestRepository repo,
            ProductRepository productRepo,
            BranchRepository branchRepo,
            UsersRepository usersRepo
    ) {
        this.repo = repo;
        this.productRepo = productRepo;
        this.branchRepo = branchRepo;
        this.usersRepo = usersRepo;
    }

    public StockRequest create(StockRequestCreateDTO dto) {
        StockRequest request = new StockRequest();

        request.setProduct(productRepo.findById(dto.getProductId()).orElseThrow());
        request.setBranch(branchRepo.findById(dto.getBranchId()).orElseThrow());
        request.setRequestedBy(usersRepo.findById(dto.getRequestedBy()).orElseThrow());

        request.setRequestedQuantity(dto.getRequestedQuantity());
        request.setStatus("PENDING");

        return repo.save(request);
    }

    public StockRequest approve(Integer id) {
        StockRequest request = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock request not found"));

        request.setStatus("APPROVED");
        return repo.save(request);
    }

    public StockRequest reject(Integer id) {
        StockRequest request = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Stock request not found"));

        request.setStatus("REJECTED");
        return repo.save(request);
    }
}
