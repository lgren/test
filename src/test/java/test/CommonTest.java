package test;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.lgren.ohers.FieldValueStrEnum;
import com.lgren.一些写法等.CommResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import pojo.Person;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;


public class CommonTest {
    @Test
    public void test1() {
        Person personOne = new Person(1L, "one", DateUtils.addDays(new Date(), -1), 2, DateUtils.addDays(new Date(), -1), DateUtils.addDays(new Date(), -1));
        Person personTwo = new Person(2L, "two", DateUtils.addDays(new Date(), -2), 2, DateUtils.addDays(new Date(), -2), DateUtils.addDays(new Date(), 0));
        Person personTree = new Person(3L, "tree", DateUtils.addDays(new Date(), -3), 2, DateUtils.addDays(new Date(), -3), DateUtils.addDays(new Date(), -3));
        Person personFour = new Person(4L, "four", DateUtils.addDays(new Date(), -4), 1, DateUtils.addDays(new Date(), -4), DateUtils.addDays(new Date(), -4));
        Person personFive = new Person(5L, "five", DateUtils.addDays(new Date(), -5), 1, DateUtils.addDays(new Date(), -3), DateUtils.addDays(new Date(), -5));
        Person personSix = new Person(6L, "six", DateUtils.addDays(new Date(), -6), 1, DateUtils.addDays(new Date(), -6), DateUtils.addDays(new Date(), -6));
        List<Person> list = Lists.newArrayList(personOne, personTwo, personTree, personFour, personFive, personSix);
        list.forEach(p -> System.out.println(p.getRealName()));

//        list.sort((r1, r2) -> {
//            if (Objects.equals(1, r1.getZjStatus()) && Objects.equals(1, r2.getZjStatus())) {//r1 r2都是未接单智荐
//                if (r1.getInsertDate().getTime() >= r2.getInsertDate().getTime()) {//r1的插入时间大于等于r2的插入时间
//                    return -1;
//                } else {//r1的插入时间小于r2的插入时间
//                    return 1;
//                }
//            } else if (Objects.equals(1, r1.getZjStatus())) {//只有r1是未接单智荐
//                return -1;
//            } else if (Objects.equals(1, r2.getZjStatus())) {//只有r2是未接单智荐
//                return 1;
//            } else {//r1 r2都不是未接单智荐
//                if (r1.getUpdateDate().getTime() >= r2.getUpdateDate().getTime()) {//r1的更新时间大于等于r2的更新时间
//                    return -1;
//                } else {//r1的更新时间小于r2的更新时间
//                    return 1;
//                }
//            }
//        });
//        list.forEach(p -> System.out.println("1排序后->" + p.getRealName()));

//        list.sort((r1, r2) -> {
//            if (Objects.equals(r2.getZjStatus(), r1.getZjStatus())){//当r1 和 r2智荐状态相同
//                if (Objects.equals(1, r2.getZjStatus())) {//当智荐状态为未抢单
//                    if (r2.getInsertDate().getTime() >= r1.getInsertDate().getTime()) {//r2的插入时间 大于等于 r1的插入时间 (新在前)
//                        return 1;//返回r2
//                    }
//                } else {
//                    if (r2.getUpdateDate().getTime() >= r1.getUpdateDate().getTime()) {//r2的更新时间 大于等于 r1的更新时间 (新在前)
//                        return 1;//返回r2
//                    }
//                }
//            } else {//当r1 和 r2智荐状态不同
//                if (Objects.equals(1, r2.getZjStatus())) {//r2位未接单状态
//                    return 1;//返回r2
//                }
//            }
//            return -1;//返回r1
//        });
//        list.forEach(p -> System.out.println("2排序后->" + p.getRealName()));

//        list.sort((r1, r2) -> {
//            if (Objects.equals(r2.getZjStatus(), r1.getZjStatus())){//当r1 和 r2智荐状态相同
//                if (Objects.equals(1, r2.getZjStatus()) && r2.getInsertDate().getTime() >= r1.getInsertDate().getTime()) {//当智荐状态为未抢单 且r2的插入时间 大于等于 r1的插入时间 (新在前)
//                    return 1;//返回r2
//                } else if (Objects.equals(2, r2.getZjStatus()) && r2.getUpdateDate().getTime() >= r1.getUpdateDate().getTime()) {//当智荐状态为已抢单 且r2的更新时间 大于等于 r1的更新时间 (新在前)
//                    return 1;//返回r2
//                }
//            } else if (Objects.equals(1, r2.getZjStatus())){//当r2为未接单状态 r1不是未接单状态
//                return 1;//返回r2
//            }
//            return -1;//返回r1
//        });
//        list.forEach(p -> System.out.println("3排序后->" + p.getRealName()));

        list.sort((r1, r2) -> {
            Integer zjStatus2 = r2.getZjStatus();
            Integer zjStatus1 = r1.getZjStatus();
            return ((Objects.equals(zjStatus2, zjStatus1)
                    && ((Objects.equals(1, zjStatus2) && r2.getInsertDate().after(r1.getInsertDate()))// 1:当智荐状态都为未抢单,r2插入时间大与r1 -> 返回r2
                    || Objects.equals(2, zjStatus2) && r2.getUpdateDate().after(r1.getUpdateDate())))// 2:当智荐状态都为已抢单,r2更新时间大与r1 -> 返回r2
                    || Objects.equals(1, zjStatus2) && !Objects.equals(1, zjStatus1)) ? 1 // 3:当r2为未接单状态 r1不是未接单状态 -> 返回r2
                    : -1;
        });//上述3条件都不满足 -> 返回r1
        list.forEach(p -> System.out.println("4排序后->" + p.getRealName()));
    }

    @Test
    public void test2() {
        Person personOne = new Person(1L, "one", DateUtils.addDays(new Date(), -1), 2, DateUtils.addDays(new Date(), -1), DateUtils.addDays(new Date(), -1));
        Person personTwo = new Person(2L, "two", DateUtils.addDays(new Date(), -2), 2, DateUtils.addDays(new Date(), -2), DateUtils.addDays(new Date(), 0));
        Person personTree = new Person(3L, "tree", DateUtils.addDays(new Date(), -3), 2, DateUtils.addDays(new Date(), -3), DateUtils.addDays(new Date(), -3));
        Person personFour = new Person(4L, "four", DateUtils.addDays(new Date(), -4), 1, DateUtils.addDays(new Date(), -4), DateUtils.addDays(new Date(), -4));
        Person personFive = new Person(5L, "five", DateUtils.addDays(new Date(), -5), 1, DateUtils.addDays(new Date(), -3), DateUtils.addDays(new Date(), -5));
        Person personSix = new Person(6L, "six", DateUtils.addDays(new Date(), -6), 1, DateUtils.addDays(new Date(), -6), DateUtils.addDays(new Date(), -6));
        List<Person> list = Lists.newArrayList(personOne, personTwo, personTree, personFour, personFive, personSix);
//        list.sort(((o1, o2) -> o1.getId() == o2.getId() ? 0 : o2.getId() > o1.getId() ? 1 : -1));
        list.sort(Person::compareTo);
        list.forEach(p -> System.out.println(p.getId() + " " + p.getRealName()));
    }

    @Test
    public void test4() {
        test3(new HashMap<>());
    }

    private String test3(Map<String, String> map) {
        String returnStr = "";
        String p1 = map.get("p1");
        String p2 = map.get("p2");
        String p3 = map.get("p3");
        //region 数据
        Person personOne = new Person(1L, "one", DateUtils.addDays(new Date(), -1), null, DateUtils.addDays(new Date(), -1), DateUtils.addDays(new Date(), -1));
        Person personTwo = new Person(2L, "two", DateUtils.addDays(new Date(), -2), 2, DateUtils.addDays(new Date(), -2), DateUtils.addDays(new Date(), 0));
        Person personTree = new Person(3L, "", DateUtils.addDays(new Date(), -3), null, DateUtils.addDays(new Date(), -3), DateUtils.addDays(new Date(), -3));
        Person personFour = null;
        Person personFive = new Person();
        personFive.setId(5L);
        Person personSix = null;
        Person personSeven = new Person(7L, "seven", null, 1, DateUtils.addDays(new Date(), -7), DateUtils.addDays(new Date(), -7));
        //endregion
        List<Person> listReturn = Lists.newArrayList(personOne, personTwo, personTree, personFive, personSix);
        listReturn.add(personFour);

        List<Person> listGet = null;
        List<Person> list = null;
        try {
            listGet = listReturn;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("1");
        }
        if (listGet == null || listGet.get(0) == null) {
            System.out.println("2");
        } else {
            list = listGet.stream().limit(10).collect(Collectors.toList());
        }
        final String[] x = new String[1];
        list.stream().filter(Objects::nonNull)
                .forEach(o -> {
//                    o.setRealName(Optional.ofNullable(o.getRealName()).filter(StringUtils::isNotBlank).map(p -> p + "改造一波").orElse("这是空的"));
                    o.setRealName(StringUtils.isNotBlank((x[0] = o.getRealName())) ? x[0] + "改造一波" : "这是空的");
                    o.setZjStatus(Optional.ofNullable(o.getZjStatus()).map(p -> p + 100).orElse(-1));
                });
        list.stream().filter(Objects::nonNull).forEach(p -> System.out.println(p.getId() + " " + p.getZjStatus() + " " +p.getRealName()));

//        Map<String, Object> map = new HashMap<>();
        return returnStr;
    }

    @Test
    public void test5() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.parallelStream()
                .forEachOrdered(System.out::println);
    }

    @Test
    public void test6() {
        List<String> list1 = new ArrayList<>(1);
        List<String> list2 = new ArrayList<>(2);
        list1.add("I am a boy");
        list2.add("I love the girl");
        list2.add("But the girl loves another girl");
//        list.stream().map(line -> line.split(" ")).map(Arrays::stream).forEach(o -> o.forEach(System.out::println));
        String test = "";
    }

    @Test
    public void test7() {
        System.out.println(new DecimalFormat(",###").format(432.232432D));
        ThreadLocalRandom random = ThreadLocalRandom.current();
    }

}
