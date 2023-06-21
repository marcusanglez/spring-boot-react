package com.example.bootforreact.repo;

import com.example.bootforreact.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
