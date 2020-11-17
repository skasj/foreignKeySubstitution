package org.example.foreignKeySubstitution.modal.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.foreignKeySubstitution.annotation.TableColumn;
import org.example.foreignKeySubstitution.annotation.TableName;

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