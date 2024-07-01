package pl.akademiaspecjalistowit.outboxpattern.mortgage;

import java.math.BigDecimal;
import java.util.UUID;

public record MortgageDto(UUID customerId, Integer durationInMonths, BigDecimal amount) {
}
