package com.studymavernspringboot.jsontest;

import java.util.List;

public interface AccountRepository2 {
    void loadJson(List<Account> accountList) throws Exception;
    void saveJson(List<Account> accountList) throws Exception;
}
