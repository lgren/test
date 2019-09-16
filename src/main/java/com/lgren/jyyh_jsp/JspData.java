package com.lgren.jyyh_jsp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * TODO
 * @create 2019-06-24 11:36
 * @since lgren
 */
@Data
@Accessors(chain = true)
public class JspData<T> {
    private String fileName;
    private String title;
    private List<T> body;
}
