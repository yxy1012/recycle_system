package com.example.recycle_system_springboot.pojo.query;

import lombok.Data;

@Data
public class AdminReqirueQuery {
    private String username;

    private String collectorname;

    private String datebyday;

    private String datebymonth;

    private String datepick;
}
