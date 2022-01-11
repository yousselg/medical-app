package com.medical.service;

import com.medical.model.blog.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> findAll();
}
