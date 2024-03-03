package com.estudo.azureservicebus.producer.domain.user;

public record RegisterDto(String login, String password, UserRole role) {
}
