package pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.BeanUtils;

/**
 * TODO
 * @author Lgren
 * @create 2018-12-07 21:32
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class Student extends Person {
    public Student(Person person) {
        if (person != null) {
            BeanUtils.copyProperties(person, this);
        }

    }
}
