package pojo;

import java.util.Date;

public class Person {
    private Long id;
    private String realName;
    private Date birthday;

    public Person() {

    }

    public Person(String realName, Date birthday) {
//        this.id = id;
        this.realName = realName;
        this.birthday = birthday;
    }

    public Person(Long id, String realName, Date birthday) {
        this.id = id;
        this.realName = realName;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", realName='" + realName + '\'' +
                ", birthday=" + birthday +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
