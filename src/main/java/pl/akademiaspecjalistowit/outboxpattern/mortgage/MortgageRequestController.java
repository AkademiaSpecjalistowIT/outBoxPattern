package pl.akademiaspecjalistowit.outboxpattern.mortgage;

import jakarta.websocket.server.PathParam;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.MortgageRequestDto;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.MortgageRequestInfoDto;
import pl.akademiaspecjalistowit.outboxpattern.mortgage.request.MortgageRequestService;

@RestController
@Slf4j
@RequestMapping("/mortgage/request")
@AllArgsConstructor
public class MortgageRequestController {

    private final MortgageRequestService mortgageRequestService;

    @PostMapping
    public UUID mortgageRequest(MortgageRequestDto mortgageRequestDto) {
        log.info("Mortgage request received.");
        return mortgageRequestService.receiveRequest(mortgageRequestDto);
    }

    @GetMapping("/{requestId}")
    public MortgageRequestInfoDto getMortgageRequestInfo(@PathParam("requestId") UUID requestId) {
        log.info("GetMortgageRequestInfo received.");
        return mortgageRequestService.getRequestProcessingInfo(requestId);
    }
}
