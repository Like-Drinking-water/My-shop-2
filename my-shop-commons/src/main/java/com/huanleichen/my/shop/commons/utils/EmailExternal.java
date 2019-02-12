package com.huanleichen.my.shop.commons.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailExternal extends SimpleEmail {
    @Override
    public String send() throws EmailException {
        this.buildMimeMessage();
        String messageId = this.sendMimeMessage();
        this.message = null;
        return messageId;
    }
}
