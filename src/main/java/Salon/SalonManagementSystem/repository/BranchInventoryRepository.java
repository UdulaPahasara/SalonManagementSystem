package Salon.SalonManagementSystem.repository;

import Salon.SalonManagementSystem.model.BranchInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BranchInventoryRepository extends JpaRepository<BranchInventory, Integer> {

    @Query(value = "SELECT bi.* FROM branch_inventory bi " +
            "INNER JOIN product_manager_branches pmb ON bi.branch_id = pmb.branch_id " +
            "WHERE bi.product_id = :productId AND pmb.user_id = :userId",
            nativeQuery = true)
    List<BranchInventory> findByProductAndManager(@Param("productId") Integer productId,
                                                  @Param("userId") Integer userId);

    List<BranchInventory> findByProduct_ProductId(Integer productId);
}
