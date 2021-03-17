package com.uom.msc.cse.sdoncloud.detection.domain.boundary;

import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.FeatureDto;

import java.util.List;

public interface LabelDetectionInterface {

    public FeatureDto detectLabel(String imgPath);
}
