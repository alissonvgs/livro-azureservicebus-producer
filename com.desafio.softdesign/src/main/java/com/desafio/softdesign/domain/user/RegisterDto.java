package com.desafio.softdesign.domain.user;

public record RegisterDto(String login, String password, UserRole role) {
}
