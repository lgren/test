package com.lgren.util;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StreamUtils;

import java.io.IOException;

/**
 * 图片工具类
 * @author lgren
 * @since 2019-11-07 16:08
 */
public class ImgUtil {

    // private String inputStreamToBase64(MultipartFile file) throws IOException {
    //     byte[] bytes = StreamUtils.copyToByteArray(file.getInputStream());
    //     return String.format("data:%s;base64,%s", file.getContentType(), new String(Base64.encodeBase64(bytes)));
    // }



    /**
     * 自动调整界面显示的图片大小
     * @param width 实际宽度
     * @param height 实际高度
     * @param maxWidth 界面展示最大宽度
     * @param maxHeight 界面展示最大高度
     * @return 数组 [0]-展示宽度 [1]-展示高度
     */
    private static Integer[] imgShowSize(int width, int height, int maxWidth, int maxHeight) {
        Integer[] showSizeArr = new Integer[2];
        double widthDiffRatio = (double) width / (double) maxWidth;
        double heightDiffRatio = (double) height / (double) maxHeight;
        if (widthDiffRatio <= 1 && heightDiffRatio <= 1) {
            showSizeArr[0] = width;
            showSizeArr[1] = height;
        } else if (widthDiffRatio > heightDiffRatio) {
            showSizeArr[0] = maxWidth;
            showSizeArr[1] = Double.valueOf(((double) height) / widthDiffRatio).intValue();
        } else {
            showSizeArr[0] = Double.valueOf(((double) width) / heightDiffRatio).intValue();
            showSizeArr[1] = maxHeight;
        }
        return showSizeArr;
    }

    public static void main(String[] args) {
        // Integer[] size = ImgUtil.imgShowSize(300, 700, 300, 200);
        // System.out.println();// 85 200
        int x = 6;
        if (x > 10)                                                                                                                                                                                                                                                                              ;{
            System.out.println("运行一下");
        }
    }
}
