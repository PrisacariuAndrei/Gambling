package gambling.project.microservice.user.dto;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    private Long id;
    private String customerName;
    public Double fiatEURO;
    public Double fiatUSD;
    public Double fiatLEI;
    private Double cryptoBitcoin;
    private Double cryptoEthereum;
    private Double cryptoLuna;
}
