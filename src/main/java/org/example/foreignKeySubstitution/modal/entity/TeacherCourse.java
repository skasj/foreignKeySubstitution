package org.example.foreignKeySubstitution.modal.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * class
 * @author ydy
 */
@Data
public class TeacherCourse implements Serializable {
    private Integer id;

    /**
     * 老师序号
     */
    private Integer teacherId;

    /**
     * 课程序号
     */
    private Integer courseId;

    private static final long serialVersionUID = 1L;
}