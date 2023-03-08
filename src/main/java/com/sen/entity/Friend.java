package com.sen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@Data@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_friend")
public class Friend implements Serializable {
    @TableId(value = "fr_id",type = IdType.AUTO)
    private Long frId;
    @NotBlank(message = "Name不能为空")
    private String name;
    @NotBlank(message = "简介不能为空")
    private String description;
    @NotBlank(message = "网站地址不能为空")
    private String website;
    private Integer flag;
    private Date createTime;
    private Date updateTime;
}
