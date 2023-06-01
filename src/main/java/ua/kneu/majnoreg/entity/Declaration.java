package ua.kneu.majnoreg.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import ua.kneu.majnoreg.entity.dict.PropertyType;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@DynamicUpdate
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne
    private User user;
    @ManyToOne
    private PropertyType propertyType;
    private String address;
    private String description;
    private String[] fileNames;
    private String status;
    private Date sendTime;
    private Date lastUpdateTime;
}
