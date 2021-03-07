package com.uom.msc.cse.sdoncloud.detection.external;

import com.uom.msc.cse.sdoncloud.detection.application.config.YAMLConfig;
import com.uom.msc.cse.sdoncloud.detection.domain.boundary.ImageHandlerInterface;
import java.util.Base64;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
@Log4j2
public class ImageHandlerService implements ImageHandlerInterface {

    @Autowired
    YAMLConfig yamlConfig;

//    public String encoder(String imagePath) {
////        imagePath = "classpath:temp-images/air-max-270-shoe-62kcVV.jpg";
//        imagePath = "/Users/pasindu/Documents/Msc/Sem 1/Software Development on Cloud Platforms/Project/best-deal-product-detection/src/main/resources/temp-images/air-max-270-shoe-62kcVV.jpg";
//        String base64Image = "";
//        File file = new File(imagePath);
//        try (FileInputStream imageInFile = new FileInputStream(file)) {
//            // Reading a Image file from file system
//            byte imageData[] = new byte[(int) file.length()];
//            imageInFile.read(imageData);
//            base64Image = Base64.getEncoder().encodeToString(imageData);
//        } catch (FileNotFoundException e) {
//            System.out.println("Image not found" + e);
//        } catch (IOException ioe) {
//            System.out.println("Exception while reading the Image " + ioe);
//        }
//        System.out.println(base64Image);
//        return base64Image;
//    }

    @Override
    public boolean deleteImg(String imgPath) {
        File file = new File(imgPath);
        file.delete();
        return true;
    }

    public String decodeImage(String imageString) throws IOException {

        //decode Base64 String to image
        String imgPath = yamlConfig.getTempImgDir()+"/sourceImage-"+ UUID.randomUUID() +".jpg";
        try (FileOutputStream imageOutFile = new FileOutputStream(imgPath)) {
            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = Base64.getDecoder().decode(imageString);
            imageOutFile.write(imageByteArray);
            log.info("Successfully Image saved : {}",imgPath);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
            imgPath = null;
        } catch (IOException ioe) {
            imgPath = null;
            System.out.println("Exception while reading the Image " + ioe);
        }
        return imgPath;

    }
}
