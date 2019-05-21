package com.javaagh.lab10;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class WebController {

    static ImageProcessorController images = new ImageProcessorController();

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String uploadFile(@RequestBody byte[] imageInBytes) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(imageInBytes);
        BufferedImage img = ImageIO.read(bais);
        String id = images.addImage(img);
        return "{id : "+id+", height: "+img.getHeight()+", width: "+img.getWidth()+"}";
    }

    @RequestMapping(value = "/image/{id}/size")
    public String getSize(@PathVariable String id) {
        try {
            return images.getSize(id);
        }
        catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
        }
    }

    @RequestMapping(value = "/image/{id}", method = RequestMethod.DELETE)
    public void removeImage(@PathVariable String id) throws IOException {
        try {
            images.removeImage(id);
        }
        catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
        }
    }

    @RequestMapping(value = "/image/{id}", produces = MediaType.IMAGE_PNG_VALUE, method = RequestMethod.GET)
    public byte[] getImage(@PathVariable String id) throws IOException {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage img = images.getImage(id);
            if(img == null) throw new NullPointerException();
            ImageIO.write(img, "png", baos);
            return baos.toByteArray();
        }
        catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
        }
    }

    @RequestMapping(value = "/image/{id}/scale/{percent}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getImage(@PathVariable String id, @PathVariable String percent) throws IOException {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(images.getScaledImage(id,Double.parseDouble(percent)), "png", baos);
            return baos.toByteArray();
        }
        catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
        }
        catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "wrong");
        }
    }

    @RequestMapping(value = "/image/{id}/histogram")
    public String getHistogram(@PathVariable String id) throws IOException {
        try {
            return images.getHistogram(id);
        }
        catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
        }
    }

    @RequestMapping(value = "/image/{id}/crop/{start}/{stop}/{width}/{height}",  produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] cropImage(@PathVariable String id, @PathVariable int start, @PathVariable int stop, @PathVariable int width, @PathVariable int height) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(images.cropImage(id, start, stop, width, height), "png", baos);
            return baos.toByteArray();
        }
        catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
        }
        catch(IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "values to crop are incorrect");
        }
    }

    @RequestMapping(value = "/image/{id}/greyscale", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getGreyscaleImage(@PathVariable String id) throws IOException {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(images.getGreyscaleImage(id), "png", baos);
            return baos.toByteArray();
        }
        catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
        }
    }

    @RequestMapping(value = "/image/{id}/blur/{radius}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getBlurredImage(@PathVariable String id, @PathVariable int radius) throws IOException {
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(images.getBlurredImage(id, radius), "png", baos);
            return baos.toByteArray();
        }
        catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "image not found");
        }
    }

}