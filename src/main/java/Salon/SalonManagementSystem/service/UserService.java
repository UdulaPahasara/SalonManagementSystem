package Salon.SalonManagementSystem.service;

import Salon.SalonManagementSystem.model.Users;
import Salon.SalonManagementSystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UsersRepository usersRepository;

    public Users authenticate(String username, String password, String jobRole) {

        Users user = usersRepository.findByUsername(username);
        if (user == null) return null;

        boolean passwordMatch = user.getPassword().equals(password); // plain-text for now
        boolean roleMatch = user.getRole().getRoleName().equalsIgnoreCase(jobRole);

        if (passwordMatch && roleMatch) {
            return user;
        }
        return null;
    }
}
