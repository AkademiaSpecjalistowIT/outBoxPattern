package pl.akademiaspecjalistowit.outboxpattern.mortgage;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/mortgage")
@AllArgsConstructor
public class MortgageController {

    private final MortgageService mortgageService;

    @PostMapping
    public Mortgage takeOutMortgage(MortgageDto mortgageDto){
        log.info("Mortgage request received.");
        return mortgageService.processMortgage(mortgageDto);
    }
}
