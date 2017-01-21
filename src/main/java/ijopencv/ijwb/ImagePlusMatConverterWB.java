package ijopencv.ijwb;

import static org.bytedeco.javacpp.opencv_core.merge;
import static org.bytedeco.javacpp.opencv_core.split;

import java.awt.image.BufferedImage;

import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.MatVector;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;

import ij.ImagePlus;


public class ImagePlusMatConverterWB {


    public opencv_core.Mat convert(ImagePlus o) {
        ImagePlus imp = o;
        // Converter ImageJ image to Frame
        Java2DFrameConverter converterToFrame = new Java2DFrameConverter();
        // To covert an ImageJ image to Frame, we must obtain the BufferedImage
        BufferedImage bi = imp.getBufferedImage();
        // Actual conversion
        org.bytedeco.javacv.Frame frame = converterToFrame.convert(bi);

        // Convert Frame to OpenCV Mat
        OpenCVFrameConverter.ToMat converterToMat = new OpenCVFrameConverter.ToMat();
        Mat img = converterToMat.convert(frame);
        // OpenCV expects a BGR image, but it is actually an RGB image, so we 
        // must split the channels and merge them in the correct order

        /* We should check if the image is in rgb or in grayscale, but that 
         remains as further work 
         */
        if (imp.getType() == ImagePlus.COLOR_RGB) { // RGB image

            MatVector rgb = new MatVector(3);
            split(img, rgb);
            Mat img2 = new opencv_core.Mat();

            Mat[] bgrarray = {rgb.get(3), rgb.get(2), rgb.get(1)};
            MatVector bgr = new MatVector(bgrarray);

            merge(bgr, img2);

            return img2;
        } else {
            return img;
        }

    }

}
