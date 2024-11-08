package com.email.trackingservice.service;

import com.email.trackingservice.dto.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SfmcClient {

    private final Map<Long, SfmcServiceResponse> sfmcServiceMockResponses;


    public SfmcClient() {
        this.sfmcServiceMockResponses = new HashMap<>();
        initializeMockData();
    }

    private void initializeMockData() {
        SfmcServiceResponse sfmcServiceResponse = new SfmcServiceResponse();
        sfmcServiceResponse.setTokenExpireDateUtc(new Date());
        sfmcServiceResponse.setCustomObjectId("f184ba6e-8863-e811-a2d0-1402ec851e95");
        sfmcServiceResponse.setCustomObjectKey("dev_cl_sendlog");
        sfmcServiceResponse.setPageSize(2500);
        sfmcServiceResponse.setPage(1);
        sfmcServiceResponse.setCount(2);
        sfmcServiceResponse.setTop(0);

        Item item = new Item();
        Key key = new Key();

        Value value = new Value();

        value.setJobid("6355503");
        value.setListid("553035");
        value.setBatchid("2");
        value.setSubid("1178207476");
        value.setTriggeredsendid(" ");
        value.setErrorcode("");
        value.setDate_sent("5/30/2018 12:03:43 AM");
        value.setApplication_triggered_mail_record_id("67544");
        value.setAccount_guid("19c19aac-e66f-11e7-b772-005056974a29");
        value.setAccount_id("33104");
        value.setAccount_email("ActionQAQueue@castlighthealth.com");
        value.setEmployer_key("walmart");
        value.setPackage_id("");
        value.setBrand_id("generic_castlight");
        value.setEmail_content_version_id("action_125_default");
        value.setClientid("1457708");
        value.setSubjectline("Here are your closest alternatives to the ER");
        value.setPreheadertext("Avoid the wait time, chaos, and expense of the emergency room with these options.");
        value.setHero_img("dev_action_125_hero_img");
        value.setTitle("Life happens: Know your alternatives to the ER");
        value.setBody_text("For many immediate medical needs, you can avoid long waits, multiple doctors, and expensive bills by using urgent care. \r\r\n\r\n\rAnd because life doesn't happen between 9 and 5, most urgent care clinics have extended hours and are open on weekends.");
        value.setCta_text("See my nearest options");
        value.setSecondary_content_1("dev_action_63449");
        value.setSecondary_content_2("dev_action_63402");
        value.setTemplate_name("action_125");
        value.setSfmc_email_name("");
        value.setPerson_contact_id("");


        item.setKeys(key);
        item.setValues(value);
        List<Item> itemsList = new ArrayList<>();

        itemsList.add(item);
        sfmcServiceResponse.setItems(itemsList);

        sfmcServiceMockResponses.put(2345L, sfmcServiceResponse);

        SfmcServiceResponse sfmcServiceResponse1 = new SfmcServiceResponse();
        sfmcServiceResponse1.setTokenExpireDateUtc(new Date());
        sfmcServiceResponse1.setCustomObjectId("f184ba6e-8863-e811-a2d0-1402ec851e95");
        sfmcServiceResponse1.setCustomObjectKey("dev_cl_sendlog");
        sfmcServiceResponse1.setPageSize(2500);
        sfmcServiceResponse1.setPage(1);
        sfmcServiceResponse1.setCount(2);
        sfmcServiceResponse1.setTop(0);

        Value val = new Value();

        val.setJobid("6355503");
        val.setListid("553035");
        val.setBatchid("2");
        val.setSubid("1178207476");
        val.setTriggeredsendid(" ");
        val.setErrorcode("14");
        val.setDate_sent("5/30/2018 12:03:43 AM");
        val.setApplication_triggered_mail_record_id("67478");
        val.setAccount_guid("19c19aac-e66f-11e7-b772-005056974rga29");
        val.setAccount_id("33155");
        val.setAccount_email("ActionQAQueue@castlighthealth.com");
        val.setEmployer_key("walmart");
        val.setPackage_id("");
        val.setBrand_id("generic_castlight");
        val.setEmail_content_version_id("action_125_default");
        val.setClientid("1457708");
        val.setSubjectline("Here are your closest alternatives to the ER");
        val.setPreheadertext("Avoid the wait time, chaos, and expense of the emergency room with these options.");
        val.setHero_img("dev_action_125_hero_img");
        val.setTitle("Life happens: Know your alternatives to the ER");
        val.setBody_text("For many immediate medical needs, you can avoid long waits, multiple doctors, and expensive bills by using urgent care. \r\r\n\r\n\rAnd because life doesn't happen between 9 and 5, most urgent care clinics have extended hours and are open on weekends.");
        val.setCta_text("See my nearest options");
        val.setSecondary_content_1("dev_action_63449");
        val.setSecondary_content_2("dev_action_63402");
        val.setTemplate_name("action_125");
        val.setSfmc_email_name("");
        val.setPerson_contact_id("");
        Item item1 = new Item();
        item1.setValues(val);
        List<Item> itemsList1 = new ArrayList<>();
        itemsList1.add(item1);
        sfmcServiceResponse1.setItems(itemsList1);

        sfmcServiceMockResponses.put(1234L, sfmcServiceResponse1);
    }

    public SfmcServiceResponse fetchStatus(long ateMailRecordId) {
        return sfmcServiceMockResponses.get(ateMailRecordId);
    }
}
