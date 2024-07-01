package pl.akademiaspecjalistowit.outboxpattern.mortgage.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.entity.MortgageRequestEntity;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.repository.MortgageRequestRepository;

@Slf4j
@Service
@AllArgsConstructor
public class MortgageRequestService {

    private final MortgageRequestRepository mortgageRequestRepository;


    public UUID receiveRequest(MortgageRequestDto mortgageRequestDto) {
        MortgageRequestEntity mortgageRequestEntity = new MortgageRequestEntity(mortgageRequestDto.customerId(),
            mortgageRequestDto.durationInMonths(),
            mortgageRequestDto.amount());
        mortgageRequestRepository.save(mortgageRequestEntity);
        return mortgageRequestEntity.getTechnicalId();
    }

    public MortgageRequestInfoDto getRequestProcessingInfo(UUID requestId) {
        return mortgageRequestRepository.findByTechnicalId(requestId)
            .map(e -> new MortgageRequestInfoDto(e.getState(), null))
            .orElseThrow(() -> new RuntimeException("Nie ma takiego wniosku"));
    }
}
