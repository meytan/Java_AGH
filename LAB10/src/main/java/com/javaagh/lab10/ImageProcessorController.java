package com.javaagh.lab10;


import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;

public class ImageProcessorController {

    int id = 100000;
    private Map<String, BufferedImage> images;

    public ImageProcessorController() {
        images  = new HashMap<>();

    }

    public String addImage(BufferedImage img){
        id++;
        String key=String.valueOf(id);
        images.put(key,img);
        return key;
    }

    public String getSize(String id){
        return "{height: " + images.get(id).getHeight() + ", width: " + images.get(id).getWidth() + "}";
    }

    public BufferedImage getImage(String id){
        return images.get(id);
    }

    public BufferedImage getScaledImage(String id, double scale){
        if(scale < 0) throw new IllegalArgumentException();
//        BufferedImage scaledImg = new BufferedImage((int)(img.getWidth() * scale), (int)(img.getHeight() * scale), BufferedImage.TYPE_INT_ARGB);
        BufferedImage before = images.get(id);
        int w = (int)(before.getWidth() * scale);
        int h = (int)(before.getHeight() * scale);
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(scale, scale);
        AffineTransformOp scaleOp =
                new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
        after = scaleOp.filter(before, after);

        return after;
    }

    public void removeImage(String id){
        images.remove(id);
    }

    public String getHistogram(String id){
        BufferedImage img = images.get(id);

        int[][] colors = {
                new int[256],
                new int[256],
                new int[256]
        };

        Raster raster = img.getRaster();
        for(int i=0; i<img.getHeight();i++){
            for(int j=0; j< img.getWidth(); j++ ){
                colors[0][raster.getSample(j,i,0)]++;
                colors[1][raster.getSample(j,i,1)]++;
                colors[2][raster.getSample(j,i,2)]++;
            }
        }
        double pixelAmount = img.getHeight() * img.getWidth();
        StringBuilder red = new StringBuilder("\"R\" : {");
        StringBuilder green = new StringBuilder("\"G\" : {");
        StringBuilder blue = new StringBuilder("\"B\" : {");
        Formatter redFormatter = new Formatter(red);
        Formatter greenFormatter = new Formatter(green);
        Formatter blueFormatter = new Formatter(blue);

        for(int i = 0; i<256; i++){
            redFormatter.format("\"%d\" : %f, ", i, colors[0][i]/pixelAmount);
            greenFormatter.format("\"%d\" : %f, ", i, colors[1][i]/pixelAmount);
            blueFormatter.format("\"%d\" : %f, ", i, colors[2][i]/pixelAmount);
        }

        red.setCharAt(red.lastIndexOf(","),'}');
        green.setCharAt(green.lastIndexOf(","),'}');
        blue.setCharAt(blue.lastIndexOf(","),'}');



        return String.format("{%s, %s, %s}",red.toString(),green.toString(),blue.toString());
    }

    public BufferedImage cropImage(String id, int start, int stop, int width, int height) throws IllegalArgumentException {
        BufferedImage img = images.get(id);
        int h = img.getHeight();
        int w = img.getWidth();
        if(start>w || stop>h || start+width>w || stop+height>h)
            throw new IllegalArgumentException();

        BufferedImage dest = img.getSubimage(start, stop, width, height);
        return dest;
    }

    public BufferedImage getGreyscaleImage(String id){
        BufferedImage bufferedImage = images.get(id);
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp op = new ColorConvertOp(cs, null);
        return op.filter(bufferedImage, null);
    }


    public BufferedImage getBlurredImage(String id, int val){
        BufferedImage image = images.get(id);
        int size = val * 2 + 1;
        float weight = 1.0f / (size * size);
        float[] data = new float[size * size];
        for (int i = 0; i < data.length; i++) {
            data[i] = weight;
        }
        Kernel kernel = new Kernel(size, size, data);
        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_ZERO_FILL, null);
        BufferedImage i = op.filter(image, null);

        return i;
    }
}
