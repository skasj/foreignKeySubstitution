package org.example.foreignKeySubstitution.modal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam {
    private String id;
    /**
     * 试题
     * todo 后续需要改进, 将题目与内容解耦;
     */
    private List<ExamRequest> context;
}
