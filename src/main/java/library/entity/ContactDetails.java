package library.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

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

    @OneToOne(mappedBy = "contactDetails")
    @JsonIgnore
    private PublishingHouse publishingHouse;

    @OneToOne(mappedBy = "contactDetails")
    @JsonIgnore
    private Author author;


}
