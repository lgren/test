package test.my;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ProToJsonTest {
    String resource = "E:\\JavaIdeaSpace\\school\\school-student\\student-controller\\src\\main\\resources\\properties\\mysql.properties";
    String[] resources = {"E:\\JavaIdeaSpace\\school\\school-student\\student-controller\\src\\main\\resources\\properties\\mysql.properties","E:\\JavaIdeaSpace\\school\\school-student\\student-controller\\src\\main\\resources\\properties\\redis.properties"};
    String resourceFolder = "E:\\JavaIdeaSpace\\school\\school-student\\student-controller\\src\\main\\resources\\properties";
    File file = new File(resource);
    File[] files = {new File("E:\\JavaIdeaSpace\\school\\school-student\\student-controller\\src\\main\\resources\\properties\\mysql.properties"), new File("E:\\JavaIdeaSpace\\school\\school-student\\student-controller\\src\\main\\resources\\properties\\redis.properties")};
    File fileFolder = new File(resourceFolder);

    @Test
    public void test1(){
        System.out.println(ProToJson.proToJSONForXD(resources, false, true));
    }


}
