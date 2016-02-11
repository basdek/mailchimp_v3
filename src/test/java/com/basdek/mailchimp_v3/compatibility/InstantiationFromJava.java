package com.basdek.mailchimp_v3.compatibility;

import com.basdek.mailchimp_v3.dto.MailChimpList;
import com.basdek.mailchimp_v3.dto.MailChimpList_CampaignDefaults;
import com.basdek.mailchimp_v3.dto.MailChimpList_Contact;
import com.basdek.mailchimp_v3.dto.MailChimpMember;

public class InstantiationFromJava {

    public MailChimpMember instantiateMailChimpMember() {
        return MailChimpMember.build(
            "minerva.mcgonagall@basdek.com", "html", "subscribed",
            null, null, "Dutch", false, null
        );
    }

    public MailChimpList_Contact instantiateMailChimpList_Contact() {
        return MailChimpList_Contact.build(
            "company", "addr", null, "city", "state", "zip", "country", null
        );
    }

    public MailChimpList instantiateMailChimpList() {
        return MailChimpList.build(
            "testlist", this.instantiateMailChimpList_Contact(), "perm reminder",
            true, new MailChimpList_CampaignDefaults("from", "from@basdek.com",
            "subject", "EN"), null, null, false, null
        );
    }

}
