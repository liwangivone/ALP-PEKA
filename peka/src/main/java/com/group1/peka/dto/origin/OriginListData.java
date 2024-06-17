package com.group1.peka.dto.origin;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OriginListData {

    private List<?> origin = new ArrayList<>();

    public OriginListData(List<?> origin) {
        this.origin = origin;
    }   
}
