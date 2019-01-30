package com.company.authorisationservice;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailAndPassword(String email, String password);
    User findByUserNameAndAndPassword(String userName, String password);
    User findByEmail(String email);
    User findByUserName(String userName);

}
