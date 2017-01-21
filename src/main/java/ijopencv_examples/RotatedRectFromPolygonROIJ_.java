package ijopencv_examples;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ij.IJ;
import ij.ImagePlus;
import ij.gui.PolygonRoi;
import ij.plugin.PlugIn;
import ijopencv.ij.PolygonRoiMatConverter;
import ijopencv.opencv.RotatedRectPolygonRoiConverter;
import org.bytedeco.javacpp.opencv_core;
import org.bytedeco.javacpp.opencv_core.Mat;
import static org.bytedeco.javacpp.opencv_core.merge;
import org.bytedeco.javacpp.opencv_imgproc;

/**
 *
 * @author jonathan
 */
public class RotatedRectFromPolygonROIJ_ implements PlugIn {

    @Override
    public void run(String arg) {
        ImagePlus imp = IJ.getImage();
        PolygonRoi pr = (PolygonRoi) imp.getRoi();
        // Converters
        PolygonRoiMatConverter pc = new PolygonRoiMatConverter();
        RotatedRectPolygonRoiConverter p2c = new RotatedRectPolygonRoiConverter();

        Mat m = pc.convert(pr,Mat.class);

        opencv_core.RotatedRect rr = opencv_imgproc.minAreaRect(m);
        opencv_core.Point2f pt = new opencv_core.Point2f(9);

        rr.points(pt);

        PolygonRoi newpr = p2c.convert(pt,PolygonRoi.class);

        imp.setRoi(newpr);

    }

}
