/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ijopencv;

import ij.gui.PointRoi;
import org.bytedeco.javacpp.opencv_core;

/**
 *
 * @author jonathan
 */
public class Points2fConverter  implements IConverter<PointRoi, opencv_core.Point2fVector> {

    @Override
    public opencv_core.Point2fVector convertTo(PointRoi pr) {
        opencv_core.Point2fVector pv = new opencv_core.Point2fVector();
        int[] xpoints = pr.getPolygon().xpoints;
        int[] ypoints = pr.getPolygon().ypoints;

        for (int i = 0; i < xpoints.length; i++) {
            pv.put(i, new opencv_core.Point2f(xpoints[i], ypoints[i]));
        }
        return pv;
    }

    @Override
    public PointRoi convertFrom(opencv_core.Point2fVector pv) {
        float[] xpoints = new float[(int) pv.size()];
        float[] ypoints = new float[(int) pv.size()];

        for (int i = 0; i < (int) pv.size(); i++) {
            xpoints[i] = (float) pv.get(i).x();
            ypoints[i] = (float) pv.get(i).y();
        }
        PointRoi pr = new PointRoi(xpoints, ypoints);
        return pr;
    }

    @Override
    public boolean canConvertTo(PointRoi I) {
        return true;
    }

    @Override
    public boolean canConvertFrom(opencv_core.Point2fVector o) {
        return true;
    }
    
}
