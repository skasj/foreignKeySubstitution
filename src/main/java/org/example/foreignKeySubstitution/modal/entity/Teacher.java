package org.example.foreignKeySubstitution.modal.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * class
 * @author ydy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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