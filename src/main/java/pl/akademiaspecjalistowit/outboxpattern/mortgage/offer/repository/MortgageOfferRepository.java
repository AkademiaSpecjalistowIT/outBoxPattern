package pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.repository;

import java.util.Optional;
import java.util.UUID;
import javax.swing.text.html.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.entity.MortgageOfferEntity;

@Repository
public interface MortgageOfferRepository extends JpaRepository<MortgageOfferEntity, Long> {
    Optional<MortgageOfferEntity> findByOfferId(UUID offerId);
}
