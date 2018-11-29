package com.lgren.entity;

import com.alibaba.fastjson.annotation.JSONCreator;
import com.lgren.annotation.FieldName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 * @author Lgren
 * @since 2018-11-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Teacher {
    @FieldName(name = "老师ID")
    private Long id;

    @FieldName(name = "老师用户名")
    private String username;

    @FieldName(name = "老师密码")
    private String password;

    @FieldName(name = "老师真实名字")
    private String realName;

    @FieldName(name = "老师学科")
    private Integer subject;

    @FieldName(name = "老师职称")
    private Integer jobTitle;

    @FieldName(name = "老师生日")
    private LocalDateTime birthday;

    @FieldName(name = "老师班级")
    private Long clazzId;
}
