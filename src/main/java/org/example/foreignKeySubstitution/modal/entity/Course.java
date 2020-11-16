package org.example.foreignKeySubstitution.modal.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * Course 课程
 * @author ydy
 */
@Data
public class Course implements Serializable {
    private Integer id;

    /**
     * 课程名称
     */
    private String name;

    private static final long serialVersionUID = 1L;
}