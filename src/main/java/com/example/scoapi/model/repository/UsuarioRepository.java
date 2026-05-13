package com.example.scoapi.model.repository;

import com.example.scoapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioRepository, Long> {
}