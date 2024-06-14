package com.eske.repo;

import com.eske.model.Adresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Adresa, Long> {
}
