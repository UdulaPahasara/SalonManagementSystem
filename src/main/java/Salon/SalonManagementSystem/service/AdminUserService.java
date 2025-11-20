package Salon.SalonManagementSystem.service;

import Salon.SalonManagementSystem.Dto.CreateUserRequest;
import Salon.SalonManagementSystem.Dto.UserDto;
import Salon.SalonManagementSystem.model.Role;
import Salon.SalonManagementSystem.model.Users;
import Salon.SalonManagementSystem.repository.RoleRepository;
import Salon.SalonManagementSystem.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminUserService {
    @Autowired private UsersRepository usersRepository;
    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder; // define bean in config

    // list all users
    public List<UserDto> listAll() {
        return usersRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    // find single user
    public UserDto findById(Integer id) {
        return usersRepository.findById(id).map(this::toDto).orElse(null);
    }

    // create
    public UserDto create(CreateUserRequest req) {
        Users u = new Users();
        u.setUsername(req.getUsername());
        // encode password
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        u.setFullName(req.getFullName());
        Role r = roleRepository.findById(req.getRoleId()).orElse(null);
        u.setRole(r);
        Users saved = usersRepository.save(u);
        return toDto(saved);
    }

    // update (doesn't change password unless provided)
    public UserDto update(Integer id, CreateUserRequest req) {
        Users u = usersRepository.findById(id).orElse(null);
        if (u == null) return null;
        u.setFullName(req.getFullName());
        u.setUsername(req.getUsername());
        if (req.getPassword() != null && !req.getPassword().isBlank()) {
            u.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        Role r = roleRepository.findById(req.getRoleId()).orElse(null);
        u.setRole(r);
        return toDto(usersRepository.save(u));
    }

    // delete
    public boolean delete(Integer id) {
        if (!usersRepository.existsById(id)) return false;
        usersRepository.deleteById(id);
        return true;
    }

    // helper DTO converter
    private UserDto toDto(Users u) {
        UserDto d = new UserDto();
        d.setId(u.getId());
        d.setUsername(u.getUsername());
        d.setFullName(u.getFullName());
        if (u.getRole() != null) {
            d.setRoleId(u.getRole().getId());
            d.setRoleName(u.getRole().getRoleName());
        }
        return d;
    }
}
