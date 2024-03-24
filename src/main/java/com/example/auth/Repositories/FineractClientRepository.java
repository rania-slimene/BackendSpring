package com.example.auth.Repositories;

import DTO.ClientDtoFineract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class FineractClientRepository implements JpaRepository<ClientDtoFineract, Long> {

}
