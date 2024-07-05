package com.studymavernspringboot.jsontest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.util.List;

public class AccountJSONRepository implements AccountRepository2 {
    private String fileName;

    public AccountJSONRepository(String filename) {
        this.fileName =filename;
    }

    @Override
    public void loadJson(List<Account> accountList) throws Exception {
        if ( accountList == null ) {
            return;
        }
        JSONParser parser = new JSONParser();
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }
        FileReader reader = new FileReader(file, Charset.defaultCharset());
        JSONObject jsonObject = (JSONObject)parser.parse(reader);

        reader.close();
//        System.out.print(jsonObject);

        JSONArray jsonArray = (JSONArray) jsonObject.get("accounts");
        accountList.clear();
        for ( Object obj : jsonArray ) {
            JSONObject element = (JSONObject)obj;
            String owner = (String) element.get("owner");
            String acNum = (String) element.get("acNum");
            Long myMoney = (Long) element.get("myMoney");
            accountList.add(new Account(owner, acNum, myMoney.intValue()));
        }
    }

    @Override
    public void saveJson(List<Account> accountList) throws Exception {
        if ( accountList == null || accountList.size() <= 0 ) {
            return;
        }
        JSONArray jsonArray = new JSONArray();
        for ( Account account : accountList ) {
            JSONObject jobj = new JSONObject();
            jobj.put("owner", account.getOwner());
            jobj.put("acNum", account.getAcNum());
            jobj.put("myMoney", account.getMyMoney());
            jsonArray.add(jobj);
        }
        JSONObject jroot = new JSONObject();
        jroot.put("accounts", jsonArray);

        FileWriter fileWriter = new FileWriter(fileName, Charset.defaultCharset());
        fileWriter.write(jroot.toString());
        fileWriter.flush();
        fileWriter.close();
    }
}