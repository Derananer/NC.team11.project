package com.company.employeeMicroservice;

import org.springframework.data.annotation.Id;

public class Department {
    @Id
    private String id;
    private String departmentName;
    private String someInfo;

    Department(String departmentName, String someInfo){
        this.departmentName = departmentName;
        this.someInfo = someInfo;
    }
    public String getId() {
        return id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getSomeInfo() {
        return someInfo;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setSomeInfo(String someInfo) {
        this.someInfo = someInfo;
    }

    @Override
    public String toString(){
        return String.format(
                "Organisation[id=%s, departmentName='%s', someInfo='%s']",
                id, departmentName, someInfo);
    }
}
