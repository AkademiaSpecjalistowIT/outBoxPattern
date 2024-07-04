package pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class MortgageOfferEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID offerId;
    private Integer proposedDurationInMonths;
    private BigDecimal proposedAmount;

    public MortgageOfferEntity(Integer proposedDurationInMonths, BigDecimal proposedAmount) {
        this.offerId = UUID.randomUUID();
        this.proposedDurationInMonths = proposedDurationInMonths;
        this.proposedAmount = proposedAmount;
    }
}
