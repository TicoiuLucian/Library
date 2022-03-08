package library.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@ToString
public class ContactDetails {


    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Column(nullable = false, length = 10, unique = true)
    private String phoneNumber;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false)
    private String address;
}
