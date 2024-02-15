package com.desafio.softdesign.repository;

import com.desafio.softdesign.domain.user.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);

}
