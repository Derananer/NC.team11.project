package employee_service.employees.model;

import java.util.Calendar;
import java.util.Date;

public class Vacation {

    public static final int NO_DATE = -1;

    private int day;
    private int month;
    private int year;
    private int numberOfDays;

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

    public int getNumberOfDays() {
        return numberOfDays;
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


}
