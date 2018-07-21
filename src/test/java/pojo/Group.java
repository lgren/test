package pojo;

import java.util.List;

public class Group {
    private String groupName;
    private List<Person> personList;

    public Group() {
    }

    public Group(String groupName, List<Person> personList) {

        this.groupName = groupName;
        this.personList = personList;
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupName='" + groupName + '\'' +
                ", personList=" + personList +
                '}';
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
