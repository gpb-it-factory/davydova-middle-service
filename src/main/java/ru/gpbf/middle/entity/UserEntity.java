package ru.gpbf.middle.entity;

public record UserEntity(Long userId, String userName) {
    @Override
    public String toString() {
        return "UserEntity{" +
                "userId=" + userId +
                '}';
    }
}
