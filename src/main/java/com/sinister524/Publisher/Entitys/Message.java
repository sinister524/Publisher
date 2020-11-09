package com.sinister524.Publisher.Entitys;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Message {
    private Long msisdn;

    private Action action;

    private Long timeStamp;
}
