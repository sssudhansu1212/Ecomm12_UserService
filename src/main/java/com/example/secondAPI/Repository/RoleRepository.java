package com.example.secondAPI.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.secondAPI.Model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{

    Optional<Role> findByName(String name);
}
