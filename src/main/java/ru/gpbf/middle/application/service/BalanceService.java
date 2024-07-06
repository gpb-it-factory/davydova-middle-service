package ru.gpbf.middle.application.service;

import java.math.BigDecimal;

public interface BalanceService {
    BigDecimal getBalance(Long userTelegramId);
}
