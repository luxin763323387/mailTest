package com.cn.springboot.springboot01.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;


/**
 * @author Steven Lu
 * @date 2018/11/17 -19:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Resource
    TemplateEngine templateEngine;

    @Resource
    MailService mailService;

    @Test
    public void test(){
        mailService.sayHello();
    }
    @Test
    public void sendSimpleMailTest(){
        mailService.sendSimpleMail("abc123456@163.com","这是第一份邮件","hello world");
    }

    @Test
    public void sendHtmlMailTest() throws MessagingException {
        String content = "<html>\n"+
                "<body>\n"+
                "<h3>hello world</h3>\n"+
                "</body>\n"+
                "</html>";
        mailService.sendHtmlMail("abc123456@163.com","这是第一份HTML邮件",content);
    }

    @Test
    public void sandAttachmentsMailTest() throws MessagingException {
        String filePath = "D:/springboot-01.zip";
        mailService.sendAttachmentsMail("abc123456@163.com","这是第一份附件邮件","这是一篇带附件",filePath);
    }

    @Test
    public void sendInlineResourceMailTest(){
        String imgPath = "D:/TIM图片20181114002152.jpg";
        String rscId = "neo001";
        String content = "<html><body> 这是有图片的邮件:<img src=\'cid:" + rscId
                +"\'></img></body><html>";
        mailService.sendInlinResourceMail("abc123456@163.com","这是第一份图片邮件",content,imgPath,rscId);

    }

    @Test
    /**
     * 模板化使用thymeleaf
     */
    public void testTmeplateMainlTest() throws MessagingException {
        Context context = new Context();
        context.setVariable("id","0001");
        String emailContent = templateEngine.process("emailTemplate",context);
        mailService.sendHtmlMail("abc123456@163.com","模板邮件",emailContent);
    }
}
