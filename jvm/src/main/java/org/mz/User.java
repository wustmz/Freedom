package org.mz;

import lombok.Data;

@Data
public class User {

    private String user;

    private int age;

    public void sout() {
        System.out.println("=======自己的加载器加载类调用方法=======");
    }

}
