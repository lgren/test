package pojo;

/**
 * TODO
 *
 * @author Lgren
 * @create 2018-09-28 11:48
 **/
public class PersonDecorator implements Component {
    private Person person;

    @Override
    public String getSexStr(Integer sex) {
        System.out.println("这里将输出性别啦");
        return person.getSexStr(sex);
    }

    @Override
    public String getIdentityStr(Integer identity) {
        return person.getIdentityStr(identity);
    }
}
