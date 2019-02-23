package set_vacation_service;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VacationedEmployee {

    private String employeeId;
    private Date[] vacationDate;
    private int[] numberOfDays;

    public VacationedEmployee() {
    }

    public VacationedEmployee(String employeeId, Date[] vacationDate, int[] numberOfDays) {
        this.employeeId = employeeId;
        this.vacationDate = vacationDate;
        this.numberOfDays = numberOfDays;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public Date[] getVacationDate() {
        return vacationDate;
    }

    public void setVacationDate(Date[] vacationDate) {
        this.vacationDate = vacationDate;
    }

    public int[] getNumberOfDays() {
        return numberOfDays;
    }

    public void setNumberOfDays(int[] numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "VacationedEmployee{" +
                "employeeId='" + employeeId + '\'' +
                ", vacationDate=" + Arrays.toString(vacationDate) +
                ", numberOfDays=" + Arrays.toString(numberOfDays) +
                '}';
    }


}
