package com.basdek.mailchimp_v3.compatibility;

import com.basdek.mailchimp_v3.dto.*;

import java.util.ArrayList;
import java.util.List;

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

    public MailChimpListMergeField instantiateMailChimpListMergeField() {
        return MailChimpListMergeField.build(
            "tag", "name", "text", null, null, null, null, null
        );
    }

    public MailChimpListSegment_Options instantiateMailChimpListSegment_Options() {
        List<MailChimpListSegment_Options_Condition> lst = new ArrayList<>();
        lst.add(new TextMergeCondition("field", "op", "value"));

        return MailChimpListSegment_Options.build(
            "all", lst
        );
    }

    public MailChimpListSegment instantiateMailChimpListSegment() {

        List<MailChimpListSegment_Options_Condition> lst = new ArrayList<>();
        lst.add(new TextMergeCondition("field", "op", "value"));

        return MailChimpListSegment.build(
            "name", new ArrayList<String>(), MailChimpListSegment_Options.build(
                "all", lst
            )
        );
    }

}
