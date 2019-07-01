package employee_service.vacations.model;

import org.springframework.data.annotation.Id;

import java.util.Calendar;
import java.util.Date;

public class Vacation {

    public static final int NO_DATE = -1;
    @Id
    private String id;

    private String employeeId;
    private int day;
    private int month;
    private int year;
    private int numberOfDays;


    public Vacation() {
    }

    public Vacation(Vacation vacation){
        this.employeeId = vacation.employeeId;
        this.day = vacation.day;
        this.month = vacation.month;
        this.year = vacation.year;
        this.numberOfDays = vacation.numberOfDays;
    }

    public Vacation(String employeeId, int day, int month, int year, int numberOfDays) {
        this.employeeId = employeeId;
        this.day = day;
        this.month = month;
        this.year = year;
        this.numberOfDays = numberOfDays;
    }

    public Vacation(String employeeId, Date vacationDate, int numberOfDays){
        this.employeeId = employeeId;
        this.numberOfDays = numberOfDays;
        setVacationStartDate(vacationDate);
    }

    public String getId() {
        return id;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public Date getVacationStartDate(){
        if( day < 0 || month < 0 || year < 0) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year,this.month,this.day);
        return calendar.getTime();
    }

    public void setVacationStartDate(Date vacationDate){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(vacationDate);
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "id='" + id + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", numberOfDays=" + numberOfDays +
                '}';
    }
}
