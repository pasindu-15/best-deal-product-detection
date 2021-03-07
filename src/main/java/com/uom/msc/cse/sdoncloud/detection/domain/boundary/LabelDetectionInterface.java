package com.uom.msc.cse.sdoncloud.detection.domain.boundary;

import java.util.List;

public interface LabelDetectionInterface {

    public List<String> detectLabel(List<String> images);
}
