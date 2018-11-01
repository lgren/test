package com.lgren.util;

import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Lgren的部分工具类
 * @author Lgren
 * @create 2018-10-17 1121
 **/
public class LgrenUtil {
    public static <T> PageInfo<T> pageInfoForList(List<T> list, Integer pageNum, Integer pageSize) {
        List<T>  resultList;
        if (CollectionUtils.isEmpty(list)) {
            resultList = new ArrayList<>(0);
            pageNum = 0;
            pageSize = 0;
        } else {
            resultList =list;
        }
        int total = resultList.size();
        int pages = (int) Math.ceil((double) total /(Objects.equals(pageSize, 0) ? 1 : pageSize));
        if (pageNum > pages) {
            resultList = new ArrayList<>(0);
        } else {
            int startNum = (pageNum - 1) * pageSize;
            int endNum = resultList.size() < (pageSize * pageNum) ? resultList.size() : (pageSize + startNum);
            resultList = resultList.subList(startNum, endNum);
        }
        PageInfo<T> newPage = new PageInfo<>();
        newPage.setPageNum(pageNum);// 当前页
        newPage.setPageSize(pageSize);// 每页显示的数量
        newPage.setPages(pages);// 总共页数
        newPage.setTotal(total);// 总数
        newPage.setList(resultList);// 数据

        newPage.setNextPage(pageNum < pageSize ? pageNum + 1 : pageSize);//
        newPage.setHasNextPage(pageNum < pages);//
        return newPage;
    }

//    public static Map<String, String> getStrObjBetweenOfTwoStr(String str, String start, String end) {
//        Map<String, String> result = new IdentityHashMap<>();
//        if (StringUtils.isBlank(str)) {
//            return result;
//        }
//        start = StringUtils.isBlank(start) ? "{" : start;
//        end = StringUtils.isBlank(end) ? "}" : end;
//        String strVar = str;
//        int startNum = strVar.indexOf(start);
//        int endNum;
//        if (start.equals(end)) {
//            int sstartNumVar = strVar.indexOf(end) + 1;
//            endNum = strVar.substring(sstartNumVar).indexOf(end) + sstartNumVar;
//        } else {
//            endNum = strVar.indexOf(end);
//        }
////        int lastNum = 0;
//        int thisNum = 0;
//        String preStr = "";
//        while(startNum != -1 && endNum != -1) {
//            if (start.equals(end)) {
//                int sstartNumVar = strVar.indexOf(end) + 1;
//                endNum = strVar.substring(sstartNumVar).indexOf(end) + sstartNumVar;
//            } else {
//                endNum = strVar.indexOf(end);
//            }
//            String findStr = strVar.substring(startNum+1, endNum);
////            int[] startAndEnd = {lastNum + startNum + 1, lastNum + endNum};
////            result.put(findStr, startAndEnd);
//            if (thisNum%2 != 0) {
//                result.put(preStr, findStr);
//            }
//            strVar = strVar.substring(endNum + 1);
////            lastNum += endNum + 1;
//            startNum = strVar.indexOf(start);
//            endNum = strVar.indexOf(end);
//            preStr = findStr;
//            thisNum++;
//        }
//        return result;
//    }
//    public static <T> FacetedPage<T> facetedPageLikePageInfoForList(List<T> list, Integer pageNum, Integer pageSize) {
//        if (CollectionUtils.isEmpty(list)) {
//            list = new ArrayList<>(0);
//            pageNum = 0;
//            pageSize = 0;
//        }
//        int total = list.size();
//        int startNum = (pageNum - 1) * pageSize;
//        int endNum = list.size() < pageSize ? list.size() : pageSize;
//        list = list.subList(startNum, startNum + endNum);
//        int pages = (int) Math.ceil(total/(Objects.equals(pageSize, 0) ? 1 : pageSize));
//
//        FacetedPage<T> newPage = new ;
//        newPage.setPageNum(pageNum);// 当前页
//        newPage.setPageSize(pageSize);// 每页显示的数量
//        newPage.setPages(pages);// 总共页数
//        newPage.setTotal(total);// 总数
//        newPage.setList(list);// 数据
//        return newPage;
//    }

//    public static <T> Map<String, Object> mapLikePageInfoForList(List<T> list, Integer pageNum, Integer pageSize) {
//        if (CollectionUtils.isEmpty(list)) {
//            list = new ArrayList<>(0);
//            pageNum = 0;
//            pageSize = 0;
//        }
//        int total = list.size();
//        int startNum = (pageNum - 1) * pageSize;
//        int endNum = list.size() < pageSize ? list.size() : pageSize;
//        list = list.subList(startNum, startNum + endNum);
//        int pages = (int) Math.ceil(total/(Objects.equals(pageSize, 0) ? 1 : pageSize));
//
//        Map<String, Object> resultMap = new HashMap<>(5);
//        resultMap.put("pageNum" ,pageNum);// 当前页
//        resultMap.put("pageSize", pageSize);// 每页显示的数量
//        resultMap.put("pages", pages);// 总共页数
//        resultMap.put("total", total);// 总数
//        resultMap.put("list", list);// 数据
//        return resultMap;
//    }

}
