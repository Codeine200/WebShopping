package com.wizardjava.services.impl;

import com.wizardjava.dao.ImageDao;
import com.wizardjava.entity.Image;
import com.wizardjava.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("imageService")
@Transactional
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageDao dao;

    @Override
    public Image getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public void saveImage(Image image) {
        dao.saveImage(image);
    }

    @Override
    public List<Image> getAllImages() {
        return dao.getAllImages();
    }

    @Override
    public List<Image> getImagesByIdProduct(Long id) {
        return dao.getImagesByIdProduct(id);
    }

    @Override
    public void deleteImageById(Long id) {
        dao.deleteImageById(id);
    }
}
