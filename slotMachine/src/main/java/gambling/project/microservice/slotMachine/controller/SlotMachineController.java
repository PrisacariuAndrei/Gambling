package gambling.project.microservice.slotMachine.controller;

import gambling.project.microservice.slotMachine.service.SlotMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Game-SlotMachine")
public class SlotMachineController {
    @Autowired
    private SlotMachineService gameService;

    @GetMapping("/spin")
    ResponseEntity<List<String>> playGame() throws InterruptedException {
        return ResponseEntity.ok(gameService.spin());
    }

    @GetMapping("/gameHistory")
    ResponseEntity<Map<Integer, List<String>>> gameHistory() {
        return ResponseEntity.ok(gameService.getGameHistory());
    }
}
