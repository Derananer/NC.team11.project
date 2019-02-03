package com.company.authorisationservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserAppRepository extends MongoRepository<UserApp, String> {
    UserApp findByEmailAndPassword(String email, String password);
    UserApp findByUsernameAndPassword(String username, String password);
    UserApp findByEmail(String email);
    UserApp findByUsername(String username);

}
