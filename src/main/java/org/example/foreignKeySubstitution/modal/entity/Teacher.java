package org.example.foreignKeySubstitution.modal.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * class
 * @author ydy
 */
@Data
public class Teacher implements Serializable {
    private Integer id;

    /**
     * 名字
     */
    private String name;

    /**
     * 手机号
     */
    private String telephone;

    private static final long serialVersionUID = 1L;
}