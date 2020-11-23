package org.example.foreignKeySubstitution.modal.entity;

import java.io.Serializable;
import java.util.Date;

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
public class ClassScheduleCard implements Serializable {
    private Integer id;

    /**
     * 上课时间
     */
    private Date course_datetime;

    private Integer teacher_course_id;

    /**
     * 课程状态：0未上，1已上，2取消，3延期
     */
    private Integer status;

    /**
     * 如果延期，延期指向下一节延期的课程
     */
    private Integer delayClassId;

    private static final long serialVersionUID = 1L;
}