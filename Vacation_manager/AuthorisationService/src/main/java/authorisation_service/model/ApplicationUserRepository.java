package authorisation_service.model;

import authorisation_service.model.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ApplicationUserRepository extends MongoRepository<ApplicationUser, String> {
    ApplicationUser findByEmailAndPassword(String email, String password);
    ApplicationUser findByUsernameAndPassword(String username, String password);
    ApplicationUser findByEmail(String email);
    ApplicationUser findByUsername(String username);

}
