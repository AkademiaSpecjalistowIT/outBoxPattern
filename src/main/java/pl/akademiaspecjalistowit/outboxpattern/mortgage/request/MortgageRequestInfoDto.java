package pl.akademiaspecjalistowit.outboxpattern.mortgage.request;

import java.util.UUID;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.entity.RequestState;

public record MortgageRequestInfoDto(RequestState status, UUID offerId) {
}
