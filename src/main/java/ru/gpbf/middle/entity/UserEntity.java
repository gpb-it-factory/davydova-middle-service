package ru.gpbf.middle.entity;

public record UserEntity(Long userId) {
    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                '}';
    }
}
