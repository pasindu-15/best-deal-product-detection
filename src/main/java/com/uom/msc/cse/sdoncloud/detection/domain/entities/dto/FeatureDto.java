package com.uom.msc.cse.sdoncloud.detection.domain.entities.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class FeatureDto {
    private List<String> features;
    private String mainFeatures;
}
