package com.example.recycle_system_springboot.pojo.query;

import lombok.Data;

@Data
public class CollectorRequireQuery {
    private int id;

    private String username;

    private String datebyday;

    private String datebymonth;

    private String datepick;
}
