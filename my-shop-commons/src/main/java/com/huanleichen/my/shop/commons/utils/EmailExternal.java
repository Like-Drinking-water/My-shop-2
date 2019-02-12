package com.huanleichen.my.shop.commons.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class EmailExternal extends SimpleEmail {
    @Override
    public String send() throws EmailException {
        String messageId = this.send();
        this.message = null;
        return messageId;
    }
}
