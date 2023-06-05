package ua.kneu.majnoreg.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private UserInformation information;
    private UserCredentials credentials;
}
