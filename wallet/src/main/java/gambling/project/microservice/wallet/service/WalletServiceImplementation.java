package gambling.project.microservice.wallet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import gambling.project.microservice.wallet.ApiException;
import gambling.project.microservice.wallet.ErrorCode;
import gambling.project.microservice.wallet.dto.request.CreateWalletDTO;
import gambling.project.microservice.wallet.dto.response.WalletDTO;
import gambling.project.microservice.wallet.entity.Wallet;
import gambling.project.microservice.wallet.repository.WalletRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WalletServiceImplementation implements WalletService {

    @Autowired
    private final WalletRepository walletRepository;
    @Autowired
    private RestTemplate template;
    private final ModelMapper modelMapper;

    public WalletServiceImplementation(WalletRepository walletRepository, ModelMapper modelMapper) {
        this.walletRepository = walletRepository;
        this.modelMapper = modelMapper;
    }

    public Wallet toWallet(CreateWalletDTO createWalletDTO) {
        return modelMapper.map(createWalletDTO, Wallet.class);
    }

    public  WalletDTO toDTO (Wallet wallet){
        return modelMapper.map(wallet, WalletDTO.class);
    }

    @Override
    public WalletDTO saveWallet(CreateWalletDTO createWalletDTO) {
        Wallet savedWallet;
        savedWallet = toWallet(createWalletDTO);
        savedWallet = walletRepository.save(savedWallet);

        return toDTO(savedWallet);
    }

    @Override
    public WalletDTO getWalletDTOById(Long id) {
        Wallet walletById = walletRepository.findById(id).orElseThrow(()-> new ApiException(ErrorCode.WALLET_NOT_FOUND));
        return toDTO(walletById);
    }

    @Override
    public List<WalletDTO> getAllWallets() {
        return walletRepository.findAll().stream()
                .map(WalletDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        Wallet wallet = walletRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.WALLET_NOT_FOUND));

        if(wallet.getFiatLEI() == 0 &&
                wallet.getFiatEURO() == 0 &&
                wallet.getFiatUSD() == 0 &&
                wallet.getCryptoBitcoin() == 0 &&
                wallet.getCryptoEthereum() == 0 &&
                wallet.getCryptoLuna() == 0) {

            walletRepository.deleteById(id);
        }
        else throw new ApiException(ErrorCode.WALLET_NOT_EMPTY);
    }

    @Override
    public Wallet addMoney(Long id, Wallet update) {
        Wallet walletById = walletRepository.findById(id).orElseThrow(()-> new ApiException(ErrorCode.WALLET_NOT_FOUND));
        if(update.getFiatEURO()!=null) {
            walletById.setFiatEURO(walletById.getFiatEURO() + update.getFiatEURO());
        }

        if(update.getFiatUSD()!=null) {
            walletById.setFiatUSD(walletById.getFiatUSD() + update.getFiatUSD());
        }

        if(update.getFiatLEI()!=null) {
            walletById.setFiatLEI(walletById.getFiatLEI() + update.getFiatLEI());
        }

        if(update.getCryptoBitcoin()!=null) {
            walletById.setCryptoBitcoin(walletById.getCryptoBitcoin() + update.getCryptoBitcoin());
        }

        if(update.getCryptoEthereum()!=null) {
            walletById.setCryptoEthereum(walletById.getCryptoEthereum() + update.getCryptoEthereum());
        }

        if(update.getCryptoLuna()!=null) {
            walletById.setCryptoLuna(walletById.getCryptoLuna() + update.getCryptoLuna());
        }

        Wallet updatedWallet = walletRepository.save(walletById);
        return updatedWallet;
    }

    @Override
    public String playSlotGame(Long playerId, Wallet update) {
        Wallet playerWallet = walletRepository.findById(playerId).orElseThrow(() -> new ApiException(ErrorCode.WALLET_NOT_FOUND));
        List<String> gameResult = new ArrayList<>();

        if(playerWallet.getFiatEURO() >= update.getFiatEURO()) {
            ResponseEntity<String> httpResponse = template.getForEntity("http://localhost:8083/Game-SlotMachine/spin", String.class);

            String responseJsonString = httpResponse.getBody();
            ObjectMapper objectMapper = new ObjectMapper();

            try {
                gameResult = objectMapper.readValue(responseJsonString, List.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else return "Insufficient Founds!";

        if (areAllElementsSame(gameResult)) {
            playerWallet.setFiatEURO(playerWallet.getFiatEURO() + 10*update.getFiatEURO());
            walletRepository.save(playerWallet);
            return "Spin result... " + gameResult + "\nYou Win... " + 10*update.getFiatEURO();
        }
        else {
            playerWallet.setFiatEURO(playerWallet.getFiatEURO() - update.getFiatEURO());
            walletRepository.save(playerWallet);
            return "Spin result... " + gameResult + "\nYou lost... " + update.getFiatEURO().toString();
        }
    }

    public static boolean areAllElementsSame(List<String> list) {
        if (list == null || list.isEmpty()) {
            return false; // Handle empty or null list
        }

        String firstElement = list.get(0);
        for (String element : list) {
            if (!element.equals(firstElement)) {
                return false; // Found a different element
            }
        }
        return true; // All elements are the same
    }
}
