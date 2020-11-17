package org.example.foreignKeySubstitution.modal.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeacherCourseDTO implements Serializable {

    private Integer id;

    /**
     * 名字
     */
    private String courseName;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 课程名称
     */
    private String teacherName;

    /**
     * 老师序号
     */
    private Integer teacherId;

    /**
     * 课程序号
     */
    private Integer courseId;

}
