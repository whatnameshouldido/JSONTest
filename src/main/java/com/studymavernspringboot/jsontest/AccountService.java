package com.studymavernspringboot.jsontest;

import java.util.ArrayList;
import java.util.List;

public interface AccountService {
    int size();
    void clear();
    boolean addAccount(String owner, String acNum, int myMoney);
    boolean addAccount(Account account);
    List<Account> getAllAccount();
    boolean deposit(String acNum, int myMoney);
    boolean withdraw(String acNum, int myMoney);
    Account findAccountByNumber( String bankAccount );
    void loadData(List<Account> list) throws Exception;
    void saveData(List<Account> list) throws Exception;
}