package com.uom.msc.cse.sdoncloud.detection.domain.boundary;

import com.google.protobuf.ByteString;

import java.io.IOException;

public interface ImageHandlerInterface {
    public String decodeImage(String imageString) throws IOException;
//    public String encoder(String imagePath);
    public boolean deleteImg(String img);

    public ByteString decodeImageToByteString(String imageString);
}
