package pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.service;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.akademiaspecjalistowit.outboxpattern.customer.AccountInfo;
import pl.akademiaspecjalistowit.outboxpattern.customer.Customer;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.MortgageDetailsService;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.model.MortgageOfferEvent;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.entity.MortgageOfferEntity;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.repository.MortgageOfferRepository;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.MortgageRequestService;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.dto.MortgageRequestDto;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.event.MortgageRequestedEvent;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.event.MortgageRequestedEventHandler;

@Slf4j
@Service
@AllArgsConstructor
public class MortgageOfferService implements MortgageRequestedEventHandler {

    private final JobScheduler jobScheduler;
    private final MortgageRequestService mortgageRequestService;
    private final MortgageDetailsService mortgageDetailsService;
    private final MortgageOfferRepository mortgageOfferRepository;
    private final MortgageOfferEventHandler mortgageOfferEventHandler;

    @Transactional
    public void prepareOffer(UUID mortgageRequestId) {
        MortgageRequestDto mortgageRequestDto = mortgageRequestService.getMortgageRequest(mortgageRequestId);
        log.info("Mortgage request processing...");
        Customer customer = getCustomer(mortgageRequestDto.customerId());
        Integer scoring = mortgageDetailsService.getScoring(customer);
        log.info("Customer {} received scoring {}", mortgageRequestDto.customerId(), scoring);
        AccountInfo accountInfo = mortgageDetailsService.getAccountInfo(customer);
        boolean customerAllowed = mortgageDetailsService.isCustomerAllowed(accountInfo, scoring);
        if (!customerAllowed) {
            log.info("Customer {} is NOT allowed to get a loan in requested amount: {}",
                mortgageRequestDto.customerId(), mortgageRequestDto.amount());
        }
        MortgageOfferEntity mortgageOfferEntity =
            new MortgageOfferEntity(mortgageRequestDto.durationInMonths(), mortgageRequestDto.amount());
        mortgageOfferRepository.save(mortgageOfferEntity);
        log.info("Mortgage request processed. Offer {} is ready", mortgageOfferEntity);
        mortgageOfferEventHandler.handleMortgageOfferEvent(new MortgageOfferEvent(mortgageOfferEntity.getOfferId(),mortgageRequestId));
    }

    private Customer getCustomer(UUID customerId) {
        return new Customer();
    }

    @Override
    public void handleMortgageRequestedEvent(MortgageRequestedEvent mortgageRequestedEvent) {
        jobScheduler.enqueue(() -> prepareOffer(mortgageRequestedEvent.requestId()));
        log.info("Mortgage offer preparation scheduled");
    }
}
