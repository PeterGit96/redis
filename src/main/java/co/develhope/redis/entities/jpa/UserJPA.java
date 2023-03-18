package co.develhope.redis.entities.jpa;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false, name = "firstname")
    private String firstName;
    @Column(nullable = false, name = "lastname")
    private String lastName;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(nullable = false, unique = true, name = "email")
    private String email;
    @Column(nullable = false, name = "password")
    private String passwordEncrypted;
    @Column(name = "domicile_address")
    private String domicileAddress;
    @Column(name = "domicile_number")
    private String domicileNumber;
    @Column(name = "domicile_city")
    private String domicileCity;
    @Column(name = "domicile_state")
    private String domicileState;
    @Column(nullable = false, unique = true, name = "fiscal_code")
    private String fiscalCode;

    public UserJPA() { }

    public UserJPA(Long id, String firstName, String lastName, String profilePicture, String email, String passwordEncrypted,
                   String domicileAddress, String domicileNumber, String domicileCity, String domicileState, String fiscalCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profilePicture = profilePicture;
        this.email = email;
        this.passwordEncrypted = passwordEncrypted;
        this.domicileAddress = domicileAddress;
        this.domicileNumber = domicileNumber;
        this.domicileCity = domicileCity;
        this.domicileState = domicileState;
        this.fiscalCode = fiscalCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordEncrypted() {
        return passwordEncrypted;
    }

    public void setPasswordEncrypted(String passwordEncrypted) {
        this.passwordEncrypted = passwordEncrypted;
    }

    public String getDomicileAddress() {
        return domicileAddress;
    }

    public void setDomicileAddress(String domicileAddress) {
        this.domicileAddress = domicileAddress;
    }

    public String getDomicileNumber() {
        return domicileNumber;
    }

    public void setDomicileNumber(String domicileNumber) {
        this.domicileNumber = domicileNumber;
    }

    public String getDomicileCity() {
        return domicileCity;
    }

    public void setDomicileCity(String domicileCity) {
        this.domicileCity = domicileCity;
    }

    public String getDomicileState() {
        return domicileState;
    }

    public void setDomicileState(String domicileState) {
        this.domicileState = domicileState;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

}
