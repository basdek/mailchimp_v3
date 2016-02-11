package com.basdek.mailchimp_v3.compatibility;

import com.basdek.mailchimp_v3.dto.MailChimpMember;

public class InstantiationFromJava {

    public MailChimpMember instantiateMailChimpMember() {
        return MailChimpMember.build(
            "minerva.mcgonagall@basdek.com", "html", "subscribed",
            null, null, "Dutch", false, null
        );
    }

}
