package sample;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class MonteCarlo extends Task<Double> {

    private GraphicsContext gc;
    private long hitted;
    private long points;
    private BufferedImage bi;
    private Random random;

    MonteCarlo(GraphicsContext gc, long points) {
        this.gc = gc;
        this.points = points;
        bi = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        g.setPaint(Color.WHITE);
        g.fillRect(0,0,bi.getWidth(),bi.getHeight());
        random = new Random();
        hitted = 0;
    }

    private double randomFromRange(double min, double max){
        return  min + (max - min) * random.nextDouble();
    }

    private int scale(double v, double A, double B, double C, double D){
         return (int) ((D-C) * (v-A) / (B-A) + C);
    }

    @Override
    protected Double call() throws Exception {
        Color color;
        long i;
        for(i = 0; i < points; i++){

            if(isCancelled()) break;

            double x = randomFromRange(-8,8);
            double y = randomFromRange(-8,8);
            if(Equation.calc(x,y)) {
                color = Color.YELLOW;
                hitted++;
            }
            else {
                color = Color.BLACK;
            }

            bi.setRGB(scale(x,-8,8,0,800), scale(y,-8,8, 800,0), color.getRGB());
            updateProgress(i,points);


            if(i%1000 == 0 ){
                gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0);

            }

        }


        return 64*((double)hitted/i);
    }


}
