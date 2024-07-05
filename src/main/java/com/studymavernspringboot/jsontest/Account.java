package com.studymavernspringboot.jsontest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {
    private String owner;
    private String acNum;
    private int myMoney;

    public Account() {
        this.owner = "";
        this.acNum = "";
        this.myMoney = 0;
    }

    public Account(String owner, String acNum, int myMoney) {
        this.owner = owner;
        this.acNum = acNum;
        this.myMoney = myMoney;
    }

    @Override
    public String toString() {
        return String.format("Account{owner='%s', acNum='%s', myMoney=%d}"
                , this.owner, this.acNum, this.myMoney);
    }
}