package com.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: Epiphany
 * @Date: 2022/3/18 - 03 - 18 - 15:57
 * @Description: com.tank
 * @version: 1.0
 */
public class PropertyMgr {
    static Properties props = new Properties();
    static {
        try {

            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  static Object get(String key){
        if(props==null){
            return null;
        }
        return props.get(key);
    }

    public  static Integer getInt(String key){
        if(props==null){
            return null;
        }
        return Integer.parseInt((String) props.get(key));
    }

}
