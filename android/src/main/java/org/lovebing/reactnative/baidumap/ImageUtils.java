package com.letto.test.baidumapdemo.npm;

/**
 * Created by Administrator. on 2017/4/25 0025.
 */

public class ImageUtils {
    
    public static void loadImage(ReadableMap option){
        if (option.hasKey("backgroundImage")) {
            String imgUri = option.getMap("backgroundImage").getString("uri");
            if (imgUri != null && imgUri.length() > 0) {
                if (imgUri.startsWith("http://") || imgUri.startsWith("https://")) {
                    ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imgUri)).build();
                    ImagePipeline imagePipeline = Fresco.getImagePipeline();
                    dataSource = imagePipeline.fetchDecodedImage(imageRequest,this);
                    DraweeController controller = Fresco.newDraweeControllerBuilder()
                            .setImageRequest(imageRequest)
                            .setControllerListener(mLogoControllerListener)
                            .setOldController(mLogoHolder.getController())
                            .build();
                    mLogoHolder.setController(controller);
                } else {
                    this.mOptions.icon(getBitmapDescriptorByName(imgUri));
                }
            }
        } else {
            options.icon(defaultIcon);
        }
    }
}
