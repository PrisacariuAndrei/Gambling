package gambling.project.microservice.wallet.service;

import gambling.project.microservice.wallet.dto.request.CreateWalletDTO;
import gambling.project.microservice.wallet.dto.response.WalletDTO;
import gambling.project.microservice.wallet.entity.Wallet;

import java.util.List;
import java.util.Map;

public interface WalletService {
    public WalletDTO saveWallet(CreateWalletDTO createWalletDTO);
    public WalletDTO getWalletDTOById(Long id);
    public List<WalletDTO> getAllWallets();
    public void deleteById(Long id);
    public Wallet addMoney(Long id, Wallet update);
    public String playSlotGame(Long playerId, Wallet update);
}
