package com.qhit.until;

import java.util.UUID;

/**
 * Created by ASUS on 2019/12/10.
 */
public class Tool {
    public static String creatUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }
}
