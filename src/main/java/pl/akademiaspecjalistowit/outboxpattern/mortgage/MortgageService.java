package pl.akademiaspecjalistowit.outboxpattern.mortgage;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.outboxpattern.customer.AccountInfo;
import pl.akademiaspecjalistowit.outboxpattern.customer.Customer;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.offer.MortgageOfferDto;

@Service
@AllArgsConstructor
@Slf4j
public class MortgageService {

    private final MortgageDetailsService mortgageDetailsService;

//    public Mortgage processMortgage(MortgageOfferDto mortgageDto) {
//        log.info("Mortgage request processing...");
//        Customer customer = getCustomer(mortgageDto.customerId());
//        Integer scoring = mortgageDetailsService.getScoring(customer);
//        log.info("Customer {} received scoring {}", mortgageDto.customerId(), scoring);
//        AccountInfo accountInfo = mortgageDetailsService.getAccountInfo(customer);
//        boolean customerAllowed = mortgageDetailsService.isCustomerAllowed(accountInfo, scoring);
//        if(customerAllowed){
//            log.info("Customer {} is allowed to get a loan in requested amount: {}",
//                mortgageDto.customerId(), mortgageDto.amount());
//            return new Mortgage();
//
//        }
//        return new Mortgage();
//        //some business logic
//    }

    private Customer getCustomer(UUID customerId) {
        return new Customer();
    }

}
