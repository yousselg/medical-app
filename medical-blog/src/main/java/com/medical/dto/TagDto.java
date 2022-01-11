package com.medical.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TagDto implements Serializable {
    private Long id;
    private String name;
}
