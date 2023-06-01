package ua.kneu.majnoreg.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import ua.kneu.majnoreg.entity.dict.UserRole;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private UserRole role;
    private String firstName;
    private String surname;
    private String middleName;
    private String email;
    private int taxpayerCode;
    private String passportSeries;
    private int passportCode;
}
