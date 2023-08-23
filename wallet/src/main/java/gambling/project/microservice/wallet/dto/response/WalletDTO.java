package gambling.project.microservice.wallet.dto.response;

import gambling.project.microservice.wallet.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDTO {
    private Long id;
    private String customerName;
    public Double fiatEURO ;
    public Double fiatUSD;
    public Double fiatLEI;
    private Double cryptoBitcoin;
    private Double cryptoEthereum;
    private Double cryptoLuna;

    public WalletDTO(Wallet wallet) {
        this.id = wallet.getId();
        this.customerName = wallet.getCustomerName();
        this.fiatEURO= wallet.getFiatEURO();
        this.fiatLEI= wallet.getFiatLEI();
        this.fiatUSD= wallet.getFiatUSD();
        this.cryptoBitcoin= wallet.getCryptoBitcoin();
        this.cryptoEthereum= wallet.getCryptoEthereum();
        this.cryptoLuna= wallet.getCryptoLuna();
    }
}
