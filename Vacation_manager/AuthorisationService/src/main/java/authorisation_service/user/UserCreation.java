package authorisation_service.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCreation {
    public String firstName;
    public String lastName;
    public String secondName;
    public String departmentName;
    public String email;
    public String username;
    public String password;

    @Override
    public String toString() {
        return "UserCreation{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
