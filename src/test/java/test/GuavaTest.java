package test;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.primitives.Ints;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;
import pojo.Person;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class GuavaTest {
    /**
     * @Description guava List的测试 Set可以是一模一样的步骤
     * @Author Lgren
     * @Date 2018/7/20 14:18
     *///guava List的测试 Set可以是一模一样的步骤
    @Test
    public void getListTest() {
        //最初始的方式
        List<Boolean> list = new ArrayList<>();
        list.add(true);
        list.add(false);
        list.add(false);
        System.out.println(list);
        //[true, false, false]

        //使用集合工具类赋值
        List<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "desa", "dsad", "dsas");
        System.out.println(list1);
        //[desa, dsad, dsas]

        //使用guava的方式
        List<Integer> list2 = Lists.newArrayList(1, 2, 321, 4);
        System.out.println(list2);
        //[1, 2, 321, 4]
    }

    /**
     * @Description Set或者List转换成 Map
     * @Author Lgren
     * @Date 2018/7/20 14:58
     *///Set或者List转换成 Map
    @Test
    public void setOrListToMapTest() {
        Set<Integer> set = Sets.newHashSet(1, 2, 3, 4);
        Map<Integer, String> get = new HashMap<Integer, String>() {{
            put(1, "one");
            put(2, "two");
            put(3, "tree");
            put(4, "four");
        }};
        //传统方式 --- 太麻烦 跳过
//        for (Object o : set) {
//            map = new HashMap();
//            map.put(o,)
//        }

        //guava方式
        Map map = Maps.asMap(set, new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return get.get(integer);
            }
        });
        System.out.println(map);

        //guava方式+java8方式
        Map map1 = Maps.asMap(set, get::get);
        System.out.println(map1);

        //java8方式 可以同时编辑key和value命名
        Map map2 = set.stream().collect(Collectors.toMap(s -> s, get::get));
        System.out.println(map2);
    }

    /**
     * @Description Map 的创建
     * @Author Lgren
     * @Date 2018/7/20 15:44
     *///Map 的创建
    @Test
    public void getMapTest() {
        //传统普通方式
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        System.out.println(map);

        //传统略升级版本
        Map<String, Integer> map1 = new HashMap<String, Integer>() {{
            put("key1", 1);
            put("key2", 2);
            put("key3", 3);
        }};
        System.out.println(map1);

        //guava版(最多5对数据)
        Map<String, Integer> map2 = ImmutableMap.of("key1", 1, "key2", 2, "key3", 3);
        System.out.println(map2);

        //guava变版
        Map<String, Integer> map3 = new ImmutableMap.Builder<String, Integer>().put("key1", 1).put("key2", 2).put("key3", 3).build();
        System.out.println(map3);
    }

    /**
     * @Description Map<String       ,       List       <       String>>的创建以及添加
     * @Author Lgren
     * @Date 2018/7/20 16:01
     *///Map<String,List<String>>的创建以及添加
    @Test
    public void mapIncludeList() {
        //guava方式
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("stringListOne", "one");
        multimap.put("stringListOne", "two");
        multimap.put("stringListOne", "tree");
        multimap.put("stringListOne", "four");
        multimap.put("stringListTwo", "1");
        multimap.put("stringListTwo", "2");
        multimap.put("stringListTwo", "3");
        multimap.put("stringListTwo", "4");
        //{stringListOne=[one, two, tree, four], stringListTwo=[1, 2, 3, 4]}

        //传统+guava方式
        Map<String, List<String>> map = new HashMap<>();
        List<String> stringListOne = Lists.newArrayList("one", "two", "tree", "four");
        List<String> stringListTwo = Lists.newArrayList("1", "2", "3", "4");
        map.put("stringListOne", stringListOne);
        map.put("stringListTwo", stringListTwo);
        stringListOne.add("five");//有效的添加
        System.out.println(map);
        //{stringListOne=[one, two, tree, four, five], stringListTwo=[1, 2, 3, 4]}
    }

    /**
     * @Description List/Set 变有规则的 String
     * @Author Lgren
     * @Date 2018/7/20 16:02
     *///List/Set 变有规则的 String
    @Test
    public void listOrSetToString() {
        List<String> list = Lists.newArrayList("aa", "bb", "cc", null);

        //传统方式
        StringBuilder str = new StringBuilder("");
        for (String aList : list) {
            str.append("-").append(aList);
        }
        System.out.println(str);
        //-aa-bb-cc-null

        //guava版本
        String str1 = Joiner.on("-").useForNull("_").join(list);
        System.out.println(str1);
        //aa-bb-cc-_

        //java8版本
        String str2 = String.join("-", "teset", "dsadsa");
        System.out.println(str2);
        //teset-dsadsa
    }

    /**
     * @Description String 变成 List或者 Map
     * @Author Lgren
     * @Date 2018/7/20 16:31
     *///String 变成 List 或 Map
    @Test
    public void stringToListOrSet() {
        String str = " 1-2 -3-4  -  - 5-6 ";

        //传统方式
        List<String> list = new ArrayList<>();
        String[] strList = str.split("-");
        for (String aStrList : strList) {
            String strVar = aStrList.replaceAll(" ", "");
            if (strVar.equals("")) {
                continue;
            }
            list.add(strVar);
        }
        System.out.println(list);
        //[1, 2, 3, 4, 5, 6]

        //guava方式 omitEmptyStrings:去掉中间为空的部分  "" trimResults:去掉中间空格  " "
        List<String> list1 = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);
        System.out.println(list1);
        //[1, 2, 3, 4, 5, 6]
    }

    /**
     * @Description Map 变一定规则的 String
     * @Author Lgren
     * @Date 2018/7/20 16:19
     *///Map 变一定规则的 String
    @Test
    public void mapToString() {
        //使用guava方式
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            put("one", 1);
            put("two", 2);
            put("tree", 3);
        }};
        String resultStr = Joiner.on(",").withKeyValueSeparator("->").join(map);
        System.out.println(resultStr);
        //one->1,tree->3,two->2
    }

    /**
     * @Description String变 Map
     * @Author Lgren
     * @Date 2018/7/20 16:45
     *///String变 Map
    @Test
    public void stringToMap() {
        String str = "   one->1   , ,  tree->3,two->2";
        Map map = Splitter.on(",").trimResults().omitEmptyStrings().withKeyValueSeparator("->").split(str);
        System.out.println(map);
    }

    /**
     * @Description 交集 差集 并集
     * @Author Lgren
     * @Date 2018/7/20 17:41
     *///交集 差集 并集
    @Test
    public void IDUSet() {
        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5);
        Set<Integer> set2 = Sets.newHashSet(3, 4, 5, 6);
        Sets.SetView<Integer> inter = Sets.intersection(set1, set2); //交集
        System.out.println(inter);
        Sets.SetView<Integer> diff = Sets.difference(set1, set2); //差集,在A中不在B中
        System.out.println(diff);
        Sets.SetView<Integer> union = Sets.union(set1, set2); //并集
        System.out.println(union);
    }

    /**
     * @Description 文件处理 写 读 复制
     * @Author Lgren
     * @Date 2018/7/21 9:19
     *///文件处理 写 读 复制
    @Test
    public void fileTest() throws IOException {
        File file = new File("D:/test.txt");
//        if (!file.exists()) {
//            file.createNewFile();
//        }
        String str = "好喜欢你\r\n你呢";
        Files.write(str.getBytes(), file);//写文件

        List<String> stringList = Files.readLines(file, Charsets.UTF_8);//读取文件
        System.out.println(stringList);

        File file1 = new File("D:/test1.txt");
        Files.copy(file, file1);//复制文件
    }

    /**
     * @Description 赋值的时候如果为空早些抛出异常
     * @Author Lgren
     * @Date 2018/7/21 13:49
     *///赋值的时候如果为空早些抛出异常
    @Test
    public void checkNotNull() {
        Person person = new Person();
        Person person1 = person;
        //guava方式
        person1 = Preconditions.checkNotNull(person, new Person());//如果person为空则空指针异常

        //java8 方式
        person1 = Objects.requireNonNull(person, "person为空");

    }

    /**
     * @Description 加密
     * @Author Lgren
     * @Date 2018/7/21 13:51
     *///加密
    @Test
    public void hashingTest() {
        String input = "hello, world";
        // 计算MD5
        System.out.println(Hashing.md5().hashBytes(input.getBytes()).toString());
        // 计算sha256
        System.out.println(Hashing.sha256().hashBytes(input.getBytes()).toString());
        // 计算sha512
        System.out.println(Hashing.sha512().hashBytes(input.getBytes()).toString());
        // 计算crc32
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()).toString());

        System.out.println(Hashing.md5().hashUnencodedChars(input).toString());
    }

    @Test
    public void mathTypeTest() {
        //传统方式
        int[] array = {1, 2, 3, 4, 5};
        int a = 4;
        boolean hasA = false;
        for (int i : array) {
            if (i == a) {
                hasA = true;
            }
        }
        System.out.println(hasA);

        //guava方式
        boolean contains = Ints.contains(array, a);
        System.out.println(contains);
    }

    @Test
    public void commonTest() {
        Optional<Person> person = Optional.ofNullable(getPerson(true));
//        System.out.println(person.orElse(new Person("Two",new Date())));

//        Optional<String> x =  person.map(p -> p.getRealName());
//        Optional<String> x1 =  person.flatMap(p -> Optional.ofNullable(p.getRealName()));
        if (!person.isPresent()) {
            System.out.println("对象不存在");
        }
//        person.orElse("");
        person.map(Person::getRealName).orElse("名字不能为空");
        person.map(Person::getRealName);

    }

    private Person getPerson(boolean type) {
        return type ? new Person("One",new Date()) : null;
    }

}


















