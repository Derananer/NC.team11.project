package com.company.employeeMicroservice;

import org.springframework.data.annotation.Id;

public class Organisation {
    @Id
    private String id;

    private String organisationName;
    private String someInfo;

    Organisation(String organisationName, String someInfo){
        this.organisationName = organisationName;
        this.someInfo = someInfo;
    }
    public String getId() {
        return id;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public String getSomeInfo() {
        return someInfo;
    }

    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    public void setSomeInfo(String someInfo) {
        this.someInfo = someInfo;
    }

    @Override
    public String toString(){
        return String.format(
                "Organisation[id=%s, organisationName='%s', someInfo='%s']",
                id, organisationName, someInfo);
    }
}
