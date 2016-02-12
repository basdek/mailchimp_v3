package com.basdek.mailchimp_v3.compatibility;

import com.basdek.mailchimp_v3.dto.*;

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

    public MailChimpInterestCategory instantiateMailChimpInterestCategory() {
        return MailChimpInterestCategory.build("title", null, "hidden");
    }

}
