package gambling.project.microservice.wallet.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name=Wallet.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class Wallet {

    public static final String TABLE_NAME = "t_wallet";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    private Double fiatEURO = 0.0;
    private Double fiatUSD = 0.0;
    private Double fiatLEI = 0.0;
    private Double cryptoBitcoin = 0.0;
    private Double cryptoEthereum = 0.0;
    private Double cryptoLuna = 0.0;
}
