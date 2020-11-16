package org.example.foreignKeySubstitution.modal.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * class
 * @author ydy
 */
@Data
public class ClassScheduleCard implements Serializable {
    private Integer id;

    /**
     * 年级
     */
    private Byte level;

    /**
     * 序号
     */
    private Integer serial;

    private static final long serialVersionUID = 1L;
}