package pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import static java.util.Optional.ofNullable;

@Data
@Accessors(chain = true)
@NoArgsConstructor
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
    private LocalDateTime insertTime;

    public Person(long l, String tree, Date date) {
        this.id = id;
        this.realName = tree;
        this.birthday = date;
    }

    @Override
    public int compareTo(Person o) {
        return this.getId() > o.getId() ? 1 : -1;
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
