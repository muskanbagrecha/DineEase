package com.mb.DineEase.model.email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.mail.Message;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailRecipient {
    private String address;
    private Message.RecipientType recipientType;

    public EmailRecipient(String address) {
        this.address = address;
        recipientType = Message.RecipientType.TO;
    }

}
