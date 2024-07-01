package pl.akademiaspecjalistowit.outboxpattern.mortgage.offer;

import java.math.BigDecimal;
import java.util.UUID;

public record MortgageOfferDto(UUID mortgageInfoDto,
                               Integer proposedDurationInMonths,
                               BigDecimal proposedAmount) {
}
