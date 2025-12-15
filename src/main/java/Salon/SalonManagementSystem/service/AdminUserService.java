package Salon.SalonManagementSystem.service;

import Salon.SalonManagementSystem.Dto.CreateUserRequest;
import Salon.SalonManagementSystem.Dto.UserDto;
import Salon.SalonManagementSystem.model.Branch;
import Salon.SalonManagementSystem.model.Role;
import Salon.SalonManagementSystem.model.Users;
import Salon.SalonManagementSystem.repository.BranchRepository;
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
    @Autowired private BranchRepository branchRepository;

    @Autowired private RoleRepository roleRepository;
    @Autowired private PasswordEncoder passwordEncoder; // define bean in config

    // list all users
    public List<UserDto> listAll() {
        return usersRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
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

        // BRANCH - ADD THIS
        if (req.getBranchId() != null) {
            Branch branch = branchRepository.findById(req.getBranchId()).orElse(null);
            u.setBranch(branch);
        }
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

        if (req.getBranchId() != null) {
            Branch branch = branchRepository.findById(req.getBranchId()).orElse(null);
            u.setBranch(branch);
        } else {
            u.setBranch(null); // Clear branch if not selected
        }
        return toDto(usersRepository.save(u));
    }

    // delete
    public boolean delete(Integer id) {
        if (!usersRepository.existsById(id)) return false;
        usersRepository.deleteById(id);
        return true;
    }

    // helper DTO converter
    public UserDto toDto(Users u) {
        UserDto dto = new UserDto();
        dto.setId(u.getId());
        dto.setUsername(u.getUsername());
        dto.setFullName(u.getFullName());
        if (u.getRole() != null) {
            dto.setRoleId(u.getRole().getId());
            dto.setRoleName(u.getRole().getRoleName());
        }
        if (u.getBranch() != null) {
            dto.setBranchId(u.getBranch().getId());
            dto.setBranchName(u.getBranch().getBranchName());
        }
        return dto;
    }

}
