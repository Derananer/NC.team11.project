package com.company.authorisationservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailAndAndPassword(String email, String password);
    User findByLoginAndAndPassword(String login, String password);
    User findByEmail(String email);
    User findByLogin(String login);

}
