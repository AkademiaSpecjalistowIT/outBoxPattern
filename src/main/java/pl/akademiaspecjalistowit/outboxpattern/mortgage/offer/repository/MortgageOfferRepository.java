package pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.entity.MortgageOfferEntity;

@Repository
public interface MortgageOfferRepository extends JpaRepository<MortgageOfferEntity, Long> {
}
