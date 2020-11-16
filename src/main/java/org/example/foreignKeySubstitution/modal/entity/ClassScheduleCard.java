package org.example.foreignKeySubstitution.modal.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * class
 * @author ydy
 */
@Data
public class ClassScheduleCard implements Serializable {
    private Integer id;

    /**
     * 上课日期
     */
    private Date course_date;

    /**
     * 上课时间
     */
    private Date course_time;

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