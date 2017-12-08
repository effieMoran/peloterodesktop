package com.pelotero.mp.helper;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Created by Eli on 8/12/2017.
 */
public class GraphicsHelper {

    public static ImageView fixEditImage(Image imageEdit){
        ImageView iv = new ImageView();
        iv.setImage(imageEdit);
        iv.setPreserveRatio(true);
        iv.setSmooth(true);
        iv.setCache(true);
        return iv;
    }
}
