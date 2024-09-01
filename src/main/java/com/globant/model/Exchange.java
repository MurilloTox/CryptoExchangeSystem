package com.globant.model;

import java.util.ArrayList;
import java.util.List;

public class Exchange {
    private Bitcoin bitcoin;
    private Etherium etherium;
    private static Exchange instance;

    private Exchange (){
        bitcoin = Bitcoin.getInstance();
        etherium = Etherium.getInstance();
    }

    public static Exchange getInstance(){
        if(instance == null){
            instance = new Exchange();
        }
        return instance;
    }


}
