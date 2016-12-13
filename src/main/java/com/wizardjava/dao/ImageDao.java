package com.wizardjava.dao;

import com.wizardjava.entity.Image;

import java.util.List;

/**
 * Created by Codeine on 09.12.2016.
 */
public interface ImageDao {
    Image getById(Long id);
    void saveImage(Image image);
    List<Image> getAllImages();
    List<Image> getImagesByIdProduct(Long id);
    void deleteImageById(Long id);
}
