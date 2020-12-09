package com.wby.web.base.mail.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @Author wby
 * @Date 2020/4/1 22:52
 */
@Data
public class MailDto implements Serializable {
    //邮件主题
    private String subject;
    //邮件内容
    private String content;
    //接收人
    private String[] tos;
}
