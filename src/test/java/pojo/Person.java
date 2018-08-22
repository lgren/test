package pojo;

import java.util.Date;
import java.util.Objects;

public class Person implements Comparable<Person> {
    private Long id;
    private String realName;
    private Date birthday;
    private Integer zjStatus;
    private Date insertDate;
    private Date updateDate;

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

    public Person(Long id, String realName, Date birthday, Integer zjStatus, Date insertDate, Date updateDate) {
        this.id = id;
        this.realName = realName;
        this.birthday = birthday;
        this.zjStatus = zjStatus;
        this.insertDate = insertDate;
        this.updateDate = updateDate;
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

    public Integer getZjStatus() {
        return zjStatus;
    }

    public void setZjStatus(Integer zjStatus) {
        this.zjStatus = zjStatus;
    }

    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public int compareTo(Person o) {
        return this.getId() > o.getId() ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(realName, person.realName) &&
                Objects.equals(birthday, person.birthday) &&
                Objects.equals(zjStatus, person.zjStatus) &&
                Objects.equals(insertDate, person.insertDate) &&
                Objects.equals(updateDate, person.updateDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, realName, birthday, zjStatus, insertDate, updateDate);
    }
}
