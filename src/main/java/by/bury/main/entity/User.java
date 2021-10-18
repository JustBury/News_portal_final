package by.bury.main.entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String MESSAGE_INVALID_USERNAME = "This field can contain only the characters of the English alphabet and the characters !._-^, the length of the field is from 6 to 20 characters.";
    private static final String MESSAGE_INVALID_FIRSTNAME = "The first name must consist only of English alphabet characters, with a length of 6 to 20 characters";
    private static final String MESSAGE_INVALID_LASTNAME = "The last name must consist only of English alphabet characters, with a length of 6 to 20 characters";
    private static final String MESSAGE_INVALID_EMAIL = "The email is not in the correct format";
    private static final String MESSAGE_INVALID_PASSWORD = "The password must contain more than 8 characters, lowercase uppercase Latin letters, numbers and special characters";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    @NotNull
    @Pattern(regexp = "([a-zA-Z!._/-^]{6,20}$)", message = MESSAGE_INVALID_USERNAME)
    private String username;

    @Column(name = "firstname")
    @NotNull
    @Pattern(regexp = "([a-zA-Z]{2,20}$)", message = MESSAGE_INVALID_FIRSTNAME)
    private String firstName;

    @NotNull
    @Pattern(regexp = "([a-zA-Z]{2,20}$)", message = MESSAGE_INVALID_LASTNAME)
    @Column(name = "lastname")
    private String lastName;

    @NotBlank(message = MESSAGE_INVALID_EMAIL)
    @Column(name = "email")
    private String Email;

    @NotNull
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$", message = MESSAGE_INVALID_PASSWORD)
    @Column(name = "password")
    private String password;


    @Column(name = "role")
    private String role;

    @Transient
    private LinkedHashMap<String,String> roles;

    @Column(name = "date_registration", updatable = false)
    @CreationTimestamp
    private Date dateRegistration;



    public User() {
        roles = new LinkedHashMap<String, String>();
        roles.put(Role.ROLE_ADMIN.toString(), "Administrator");
        roles.put(Role.ROLE_MODERATOR.toString(), "Moderator");
        roles.put(Role.ROLE_GUEST.toString(), "Guest");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String login) {
        this.username = login;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateRegistration() {
        return dateRegistration;
    }

    public void setDateRegistration(Date dateRegistration) {
        this.dateRegistration = dateRegistration;
    }

    public LinkedHashMap<String, String> getRoles() {
        return roles;
    }

    public void setRoles(LinkedHashMap<String, String> roles) {
        this.roles = roles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id
                && Objects.equals(username, user.username)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(Email, user.Email)
                && Objects.equals(dateRegistration, user.dateRegistration)
                && Objects.equals(password, user.password)
                && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        final int prime = 51;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((username == null) ? 0 : username.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((Email == null) ? 0 : Email.hashCode());
        result = prime * result + ((dateRegistration == null) ? 0 : dateRegistration.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((role == null) ? 0 : role.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '@' +
                " Id=" + id +
                ", userName=" + username +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", Email=" + Email +
                ", Date registration=" + dateRegistration +
                ", password=" + password +
                ", role= " + role;
    }
}
