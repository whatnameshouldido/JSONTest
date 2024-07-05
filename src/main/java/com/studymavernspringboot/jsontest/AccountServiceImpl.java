package com.studymavernspringboot.jsontest;

import java.util.ArrayList;
import java.util.List;

public class AccountServiceImpl implements AccountService {
    AccountRepository2 accountRepository2;

    public AccountServiceImpl(String arg1, String fileName) throws Exception {
        if ("-j".equals(arg1)) {
            accountRepository2 = new AccountJSONRepository(fileName);
        } else if ("-t".equals(arg1)) {
            accountRepository2 = new AccountFileRepository(fileName);
        } else {
            throw new Exception("Error : You need program arguments (-j/-t) (filename) !");
        }
    }

    public void setInitRepository(String arg1, String fileName) throws Exception {
        if ("-j".equals(arg1)) {
            accountRepository2 = new AccountJSONRepository(fileName);
        } else if ("-t".equals(arg1)) {
            accountRepository2 = new AccountFileRepository(fileName);
        } else {
            throw new Exception("Error : You need program arguments (-j/-t) (filename) !");
        }
    }

    private List<Account> accountList = new ArrayList<>();

    public int size() {
        return this.accountList.size();
    }

    public void clear() {
        this.accountList.clear();
    }

    public boolean addAccount(String owner, String acNum, int myMoney) {
        return this.accountList.add(new Account(owner, acNum, myMoney));
    }

    public boolean addAccount(Account account) {
        return this.accountList.add(account);
    }

    public List<Account> getAllAccount() {
        return this.accountList;
    }

    public boolean deposit(String acNum, int myMoney) {
        Account account = this.findAccountByNumber(acNum);
        if (account == null) {
            return false;
        }
        account.setMyMoney(account.getMyMoney() + myMoney);
        return true;
    }

    public boolean withdraw(String acNum, int myMoney) {
        Account account = this.findAccountByNumber(acNum);
        if (account == null) {
            return false;
        }
        if (account.getMyMoney() >= myMoney) {
            account.setMyMoney(account.getMyMoney() - myMoney);
            return true;
        } else {
            return false;
        }
    }

    public Account findAccountByNumber(String acNum) {
        if (acNum == null || acNum.isEmpty()) {
            return null;
        }
        for (Account account : accountList) {
            if (acNum.equals(account.getAcNum())) {
                return account;
            }
        }
        return null;
    }

    public void loadData(List<Account> list) throws Exception {
        accountRepository2.loadJson(list);
    }

    public void saveData(List<Account> list) throws Exception {
        accountRepository2.saveJson(list);
    }
}
