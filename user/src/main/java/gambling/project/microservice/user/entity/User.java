package gambling.project.microservice.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Setter
@Getter
@Builder
@Table(name = User.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    public static final String TABLE_NAME = "t_user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String password;
}
