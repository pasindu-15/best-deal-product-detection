package com.uom.msc.cse.sdoncloud.detection.domain.boundary;

import com.google.protobuf.ByteString;
import com.uom.msc.cse.sdoncloud.detection.domain.entities.dto.FeatureDto;

import java.util.List;

public interface LabelDetectionInterface {

    public FeatureDto detectLabel(String imgPath);

    public FeatureDto detectLabelFromByteStringImage(ByteString imageByteString);
}
