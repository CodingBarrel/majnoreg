package ua.kneu.majnoreg.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import ua.kneu.majnoreg.entity.dict.UserRole;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DynamicUpdate
public class UserInformation implements Serializable {

    @Serial
    private static final long serialVersionUID = -8250692093330813422L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private UserRole role;
    private String firstName;
    private String surname;
    private String middleName;
    private int taxpayerCode;
    private String passportSeries;
    private int passportCode;

}
