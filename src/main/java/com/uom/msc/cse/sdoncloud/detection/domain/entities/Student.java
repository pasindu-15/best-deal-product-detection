package com.uom.msc.cse.sdoncloud.detection.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Setter
@Getter
@AllArgsConstructor
public class Student {

    @Id
    Integer id;

    String name;
}
