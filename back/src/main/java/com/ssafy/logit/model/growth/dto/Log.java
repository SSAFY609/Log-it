package com.ssafy.logit.model.growth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Log implements Comparable<Log> {

    private int idx;

    private String date;

    private boolean written;

    @Override
    public int compareTo(Log o) {
        return this.idx - o.idx;
    }
}
