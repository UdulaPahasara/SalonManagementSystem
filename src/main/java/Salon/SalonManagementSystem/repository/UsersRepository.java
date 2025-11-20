package Salon.SalonManagementSystem.repository;

import Salon.SalonManagementSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findByUsername(String username);
}
