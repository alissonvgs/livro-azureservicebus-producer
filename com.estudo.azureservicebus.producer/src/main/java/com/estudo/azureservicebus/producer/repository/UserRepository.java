package com.estudo.azureservicebus.producer.repository;

import com.estudo.azureservicebus.producer.domain.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);

}
