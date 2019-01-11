package com.company.employeeMicroservice;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface OrganisationRepository extends MongoRepository<Organisation, String> {

    Organisation findByOrganisationName(String organisationName);

}
