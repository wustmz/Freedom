package org.mz.mq;

import lombok.Data;

@Data
public class MessageObj {
    boolean ACK;
    int id;
    String name;
    String value;
}
