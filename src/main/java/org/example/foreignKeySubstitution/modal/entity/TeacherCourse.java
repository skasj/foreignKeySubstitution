package org.example.foreignKeySubstitution.modal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * class
 * @author ydy
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
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