package com.studymavernspringboot.jsontest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AccountFileRepository implements AccountRepository2 {
    public String fileName;

    public AccountFileRepository(String filename) {
        fileName = filename;
    }

    @Override
    public void loadJson(List<Account> accountList) throws Exception {
        File file = new File(fileName);
        if (!file.exists()) {
            return; // 파일이 없을때 실행하면 예외 없도록 처리함
        }
        BufferedReader inFile = new BufferedReader(new FileReader(file));
        String sLine = null;
        accountList.clear();
        while ((sLine = inFile.readLine()) != null) {
            Account account = new Account();
            String[] items = sLine.split(",");
            account.setOwner(items[0]);
            account.setAcNum(items[1]);
            account.setMyMoney(Integer.parseInt(items[2]));
            accountList.add(account);
        }
    }

    @Override
    public void saveJson(List<Account> accountList) throws Exception {
        FileOutputStream fileOut = new FileOutputStream(fileName);
        OutputStreamWriter writer = new OutputStreamWriter(fileOut, StandardCharsets.UTF_8);

        for (Account account : accountList) {
            String str = String.format("%s,%s,%d,\n"
                    , account.getOwner(), account.getAcNum(), account.getMyMoney());
            writer.write(str);
        }
        writer.close();
    }
}