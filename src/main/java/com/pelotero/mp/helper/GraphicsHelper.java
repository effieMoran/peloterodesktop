package com.pelotero.mp.helper;

import com.pelotero.mp.constants.Constants;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * Created by Eli on 8/12/2017.
 */
public class GraphicsHelper {

    public static ImageView fixEditImage(Class<?> objectClass){
        Image imageEdit = new Image(objectClass.getResourceAsStream(Constants.EDIT_BUTTON_URL));
        ImageView iv = new ImageView();
        iv.setImage(imageEdit);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        return iv;
    }

    public static ImageView fixReportImage(Class<?> objectClass){
        Image imageEdit = new Image(objectClass.getResourceAsStream(Constants.REPORT_BUTTON_URL));
        ImageView iv = new ImageView();
        iv.setImage(imageEdit);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        return iv;
    }

}
