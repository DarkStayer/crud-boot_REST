package com.andrew.common.crudboot.crudboot.Service;

import com.andrew.common.crudboot.crudboot.DAO.RoleRepository;
import com.andrew.common.crudboot.crudboot.Model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Role getById(Long id) {
        Role role = null;
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()){
            role = optionalRole.get();
        }
        return role;
    }

    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findRoleByName(roleName);
    }

    @Override
    public Set<Role> getAllRoles (){
        return roleRepository.getAllRoles();
    }
}
