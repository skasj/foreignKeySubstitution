package org.example.foreignKeySubstitution.modal.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * class
 * @author ydy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Class implements Serializable {
    private Integer id;

    /**
     * 年级
     */
    private Integer level;

    /**
     * 序号
     */
    private Integer serial;

    private static final long serialVersionUID = 1L;
}