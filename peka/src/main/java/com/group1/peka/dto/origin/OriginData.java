package com.group1.peka.dto.origin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OriginData {

    private int originID;

    private String originName;


    public OriginData(int originID, String originName) {
        this.originID = originID;
        this.originName = originName;
    }
}
