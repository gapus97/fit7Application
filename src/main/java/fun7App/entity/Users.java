package fun7App.entity;

import javax.persistence.Id;

import javax.persistence.*;

@Entity
public class Users {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "timezone")
    private String timezone;
    @Column(name = "userId")
    private String userId;
    @Column(name = "countryCode")
    private String countryCode;
    @Column(name = "timesLogged")
    private int timesLogged;

    public Users() {
    }

    public Users(String timezone, String userId, String countryCode) {
        this.timezone = timezone;
        this.userId = userId;
        this.countryCode = countryCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public int getTimesLogged() {
        return timesLogged;
    }

    public void setTimesLogged(int timesLogged) {
        this.timesLogged = timesLogged;
    }

}
