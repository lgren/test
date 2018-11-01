package pojo;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static java.util.Optional.ofNullable;

public class Person implements Comparable<Person>,Cloneable,Component {
    private Long id;
    private String realName;
    private Date birthday;
    private Integer zjStatus;
    private Date insertDate;
    private Date updateDate;
    private Integer[] intArr;
    private Integer sex;
    private Integer identity;

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

    public Integer[] getIntArr() {
        return intArr;
    }

    public void setIntArr(Integer[] intArr) {
        this.intArr = intArr;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
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
                Objects.equals(updateDate, person.updateDate) &&
                Arrays.equals(intArr, person.intArr) &&
                Objects.equals(sex, person.sex) &&
                Objects.equals(identity, person.identity);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, realName, birthday, zjStatus, insertDate, updateDate, sex, identity);
        result = 31 * result + Arrays.hashCode(intArr);
        return result;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ",\"realName\":\"" + realName + '\"' +
                ",\"birthday\":\"" + birthday + '\"' +
                ",\"zjStatus\":" + zjStatus +
                ",\"insertDate\":\"" + insertDate + '\"' +
                ",\"updateDate\":\"" + updateDate + '\"' +
                ",\"intArr\":" + Arrays.toString(intArr) +
                ",\"sex\":" + sex +
                ",\"identity\":" + identity +
                "}";
    }

    @Override
    public Object clone()   {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getSexStr(Integer sex) {
        return ofNullable(this.sex).filter(o -> o == 1 || o == 2).map(o -> Objects.equals(o, 1) ? "男" : "女").orElse("");
    }

    @Override
    public String getIdentityStr(Integer identity) {
        return ofNullable(this.identity).map(o -> {
            switch (o) {
                case 1: return "学生";
                case 2: return "老师";
                case 3: return "警察";
                case 4: return "医生";
                case 5: return "科学家";
                case 6: return "建筑师";
                case 0: default: return null;
            }
        }).orElse("");
    }
}
