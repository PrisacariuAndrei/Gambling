package gambling.project.microservice.wallet.controller;

import gambling.project.microservice.wallet.dto.request.CreateWalletDTO;
import gambling.project.microservice.wallet.dto.response.WalletDTO;
import gambling.project.microservice.wallet.entity.Wallet;
import gambling.project.microservice.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping("/")
    ResponseEntity<WalletDTO> saveWallet(@RequestBody CreateWalletDTO wallet){
        return new ResponseEntity<>(walletService.saveWallet(wallet), HttpStatus.CREATED);
    }

    @GetMapping("/")
    ResponseEntity<List<WalletDTO>> getAllWallets(){
        return ResponseEntity.ok(walletService.getAllWallets());
    }

    @GetMapping("/{walletId}")
    ResponseEntity<WalletDTO> getWalletById(@PathVariable("walletId") final Long walletId) {
        return ResponseEntity.ok(walletService.getWalletDTOById(walletId));
    }

    @PatchMapping("/{walletId}")
    ResponseEntity<Wallet> addMoney(@PathVariable("walletId") final Long walletId, @RequestBody Wallet update) {
        return ResponseEntity.ok(walletService.addMoney(walletId,update));
    }

    @PatchMapping("/play-slotGame/user/{id_p}")
    ResponseEntity<String> playSlotGame(@PathVariable("id_p") final Long id_p, @RequestBody Wallet update) {
        return ResponseEntity.ok(walletService.playSlotGame(id_p, update));
    }

    @DeleteMapping("/{walletId}")
    public void deleteById(@PathVariable("walletId") Long walletId) {
        walletService.deleteById(walletId);
    }
}
