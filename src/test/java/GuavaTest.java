import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import com.google.common.primitives.Ints;
import org.junit.Test;
import pojo.Person;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GuavaTest {
    private Date nowDate = new Date();

    @Test
    public void guavaList的添加元素测试() {
        //region 最初始的方式
        List<Boolean> list = new ArrayList<>();
        list.add(true);
        list.add(false);
        list.add(false);
        System.out.println(list);//endregion [true, false, false]

        //region 使用集合工具类赋值
        List<String> list1 = new ArrayList<>();
        Collections.addAll(list1, "desa", "dsad", "dsas");
        System.out.println(list1);//endregion [desa, dsad, dsas]

        //region 使用guava的方式
        List<Integer> list2 = Lists.newArrayList(1, 2, 321, 4);
        System.out.println(list2);//endregion [1, 2, 321, 4]

    }

    @Test
    public void Set或者List转换成Map() {
        Set<Integer> set = Sets.newHashSet(1, 2, 3, 4);
        Map<Integer, String> get = new HashMap<Integer, String>() {{
            put(1, "one");
            put(2, "two");
            put(3, "tree");
            put(4, "four");
        }};
        //传统方式 --- 太麻烦 跳过

        //region guava方式
        Map<Integer, String> map = Maps.asMap(set, new com.google.common.base.Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return get.get(integer);
            }
        });
        System.out.println(map);//endregion

        //region guava方式+java8方式
        Map<Integer, Object> map1 = Maps.asMap(set, get::get);
        System.out.println(map1);//endregion

        //region java8方式 可以同时编辑key和value命名
        Map map2 = set.stream().collect(Collectors.toMap(s -> s, get::get));
        System.out.println(map2);//endregion
    }

    @Test
    public void Map的创建() {
        //region 传统普通方式 推荐!!!
        Map<String, Integer> map = new HashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);
        map.put("key3", 3);
        System.out.println(map);//endregion

        //region传统略升级版本
        Map<String, Integer> map1 = new HashMap<String, Integer>() {{
            put("key1", 1);
            put("key2", 2);
            put("key3", 3);
        }};
        System.out.println(map1);//endregion

        //region guava版(最多5对数据)
        Map<String, Integer> map2 = ImmutableMap.of("key1", 1, "key2", 2, "key3", 3);
        System.out.println(map2);//endregion

        //region guava变版
        Map<String, Integer> map3 = new ImmutableMap.Builder<String, Integer>()
                .put("key1", 1).put("key2", 2).put("key3", 3).build();
        System.out.println(map3);//endregion
    }

    @Test
    public void Map中还有List等的创建以及添加() {
        //region guava方式
        Multimap<String, String> multimap = ArrayListMultimap.create();
        multimap.put("stringListOne", "one");
        multimap.put("stringListOne", "two");
        multimap.put("stringListOne", "tree");
        multimap.put("stringListOne", "four");
        multimap.put("stringListTwo", "1");
        multimap.put("stringListTwo", "2");
        multimap.put("stringListTwo", "3");
        multimap.put("stringListTwo", "4");//endregion {stringListOne=[one, two, tree, four], stringListTwo=[1, 2, 3, 4]}

        //region 传统+guava方式
        Map<String, List<String>> map = new HashMap<>();
        List<String> stringListOne = Lists.newArrayList("one", "two", "tree", "four");
        List<String> stringListTwo = Lists.newArrayList("1", "2", "3", "4");
        map.put("stringListOne", stringListOne);
        map.put("stringListTwo", stringListTwo);
        stringListOne.add("five");//有效的添加
        System.out.println(map);//endregion {stringListOne=[one, two, tree, four, five], stringListTwo=[1, 2, 3, 4]}
    }

    @Test
    public void List_Set变有规则的String() {
        List<String> list = Lists.newArrayList("aa", "bb", "cc", null, "", "  ");

        //region 传统方式
        StringBuilder str = new StringBuilder();
        for (String aList : list) {
            str.append("-").append(aList);
        }
        System.out.println(str);//endregion -aa-bb-cc-null

        //region guava版本
//        String str1 = Joiner.on("-").useForNull("_").join(list);
        String str1 = Joiner.on("-").skipNulls().join(list);
        System.out.println(str1);//endregion aa-bb-cc-_--

        //region java8版本
        String str2 = String.join("-", "teset", "dsadsa");
        System.out.println(str2);//endregion teset-dsadsa
    }

    @Test
    public void String变成List_Map() {
        String str = " 1-2 -3-4  -  - 5-6 ";

        //region 传统方式
        List<String> list = new ArrayList<>();
        String[] strList = str.split("-");
        for (String aStrList : strList) {
            String strVar = aStrList.replaceAll(" ", "");
            if (strVar.equals("")) {
                continue;
            }
            list.add(strVar);
        }
        System.out.println(list);//endregion [1, 2, 3, 4, 5, 6]

        //region guava方式 omitEmptyStrings:去掉中间为空的部分  "" trimResults:去掉中间空格  " "
        List<String> list1 = Splitter.on("-").omitEmptyStrings().trimResults().splitToList(str);
        System.out.println(list1);//endregion [1, 2, 3, 4, 5, 6]
    }

    @Test
    public void Map变一定规则的String() {
        //region 使用guava方式
        Map<String, Integer> map = new HashMap<String, Integer>() {{
            final long serialVersionUID = 1L;// 如果不加这句话 可能通过dubbo等通道的时候会报错 序列化方面的原因
            put("one", 1);
            put("two", 2);
            put("tree", 3);
        }};
        String resultStr = Joiner.on(",").withKeyValueSeparator("->").join(map);
        System.out.println(resultStr);//endregion one->1,tree->3,two->2
    }

    @Test
    public void String变Map() {
        String str = "   one->1   , ,  tree->3,two->2";
        Map<String, String> map = Splitter.on(",").trimResults().omitEmptyStrings().withKeyValueSeparator("->").split(str);
        System.out.println(map);
    }

    @Test
    public void 交集_差集_并集() {
        Person person1 = new Person(1L,"one",new Date());
        Person person2 = new Person(2L,"two",new Date());
        Person person3 = new Person(3L,"tree",new Date());
        Person person4 = new Person(4L,"four",new Date());

//        guava
        List<Person> list1 = Lists.newArrayList(person1,person2);
        List<Person> list2 = Lists.newArrayList(person2, person3, person4);
        System.out.println(list1);
        Set<Person> set1 = new LinkedHashSet<>(list1);
        Set<Person> set2 = new LinkedHashSet<>(list2);
//        Set<Integer> set1 = Sets.newHashSet(1, 2, 3, 4, 5);
//        Set<Integer> set2 = Sets.newHashSet(3, 4, 5, 6);
        Sets.SetView<Person> inter = Sets.intersection(set1, set2); //交集
        System.out.println(inter);
        Sets.SetView<Person> diff = Sets.difference(set1, set2); //差集,在A中不在B中
        System.out.println(diff);
        Sets.SetView<Person> union = Sets.union(set1, set2); //并集
        System.out.println(union);
//          Spring中
//        //并集
//        Collection<String> unionO = CollectionUtils.union(list1, list2);
//        //交集
//        Collection<String> intersectionO = CollectionUtils.intersection(list1, list2);
//        //交集的补集
//        Collection<String> disjunctionO = CollectionUtils.disjunction(list1, list2);
//        //集合相减
//        Collection<String> subtrlist1ctO = CollectionUtils.subtract(list1, list2);
    }

    @Test
    public void 文件处理_写_读_复制() throws IOException {
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

    @Test
    public void 赋值的时候如果为空早些抛出异常() {
        Person person = new Person();
        Person person1 = person;
        //guava方式
        person1 = Preconditions.checkNotNull(person, new Person());//如果person为空则空指针异常

        //java8 方式 建议
        person1 = Objects.requireNonNull(person, "person为空");

    }

    @Test
    public void 加密() {
        String input = "hello, world";
        // 计算MD5
        System.out.println(Hashing.md5().hashBytes(input.getBytes()));
        // 计算sha256
        System.out.println(Hashing.sha256().hashBytes(input.getBytes()));
        // 计算sha512
        System.out.println(Hashing.sha512().hashBytes(input.getBytes()));
        // 计算crc32
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()));

        System.out.println(Hashing.md5().hashUnencodedChars(input));
    }

    @Test
    public void int数组中是否包含置顶数字() {
        //region 传统方式
        int[] array = {1, 2, 3, 4, 5};
        int a = 4;
        boolean hasA = false;
        for (int i : array) {
            if (i == a) {
                hasA = true;
            }
        }
        System.out.println(hasA);//endregion

        //region guava方式
        boolean contains = Ints.contains(array, a);
        System.out.println(contains);//endregion
    }

    @Test
    public void MD5加密() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //确定计算方法
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newHouseMD5 = base64en.encode(md5.digest("你好哇".getBytes(StandardCharsets.UTF_8)));
        String newPreferenceMD5 = base64en.encode(md5.digest("我不好".getBytes(StandardCharsets.UTF_8)));

        System.out.println(newHouseMD5);
        System.out.println(newPreferenceMD5);
    }

    @Test
    public void biMap可以根据value查询key() {
        BiMap<Integer, String> biMap = HashBiMap.create();
        biMap.put(0, "零");
        biMap.put(1, "一");
        biMap.put(2, "二");
        biMap.put(3, "三");
        System.out.println();

    }

    @Test
    public void Table() {
        // 就像是两个key得到一个value值
        Table<String, String, String> employeeTable = HashBasedTable.create();
        employeeTable.put("IBM", "101","Mahesh");
        employeeTable.put("IBM", "102","Ramesh");
        employeeTable.put("IBM", "103","Suresh");
        employeeTable.put("Microsoft", "111","Sohan");
        employeeTable.put("Microsoft", "112","Mohan");
        employeeTable.put("Microsoft", "113","Rohan");
        employeeTable.put("TCS", "121","Ram");
        employeeTable.put("TCS", "122","Shyam");
        employeeTable.put("TCS", "123","Sunil");
        System.out.println(employeeTable);
    }

    @Test
    public void 计时器() throws InterruptedException {
        Stopwatch watch1 = Stopwatch.createUnstarted();// 累计时间
        Stopwatch watch = Stopwatch.createStarted();
        Thread.sleep(10000);
        System.out.println(" 用时: " + watch.toString());
        System.out.println();
    }

    @Test
    public void 范围() {
        Range<Integer> range = Range.open(1, 5);
        System.out.println();
    }

    @Test
    public void 公共测试1() {
        Person person1 = new Person(1L,"one",new Date());
        Person person2 = new Person(2L,"two",new Date());
        Person person3 = new Person(3L,"tree",new Date());
        Person person4 = new Person(4L,"four",new Date());

//        guava
        List<Person> list1 = Lists.newArrayList(person1, person2, person3, person4);
//        list1.stream().collect(Collectors.toMap())

        // 将Stream转换成容器或Map
        Stream<String> stream = Stream.of("I", "love", "you", "too");
        List<String> list = stream.collect(Collectors.toList()); // (1)
        Set<String> set = stream.collect(Collectors.toSet()); // (2)
        Map<String, Integer> map = stream.collect(Collectors.toMap(Function.identity(), String::length)); // (3)
    }

    @Test
    public void 公共测试2() {
        List<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21);
        getAllStream(list, 10);
        System.out.println();
    }
    private  <T> List<List<T>> getAllStream(List<T> list, Integer pageSize) {
        int total = list.size();
        int pages = (int) Math.ceil((double) total / Math.max(pageSize, 1));
        List<List<T>> result =  new ArrayList<>(pages);
        for (int i = 0; i < pages; i++) {
            result.add(list.subList(i * pageSize, Math.min((i + 1) * pageSize, total)));
        }
        return result;
    }

    @Test
    public void 公共测试3() {
        System.out.println(LocalDate.now().minusDays(10));
    }

    @Test
    public void 公共测试4() {

    }
}

















