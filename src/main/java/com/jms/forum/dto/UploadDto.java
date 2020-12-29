package com.jms.forum.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jamison
 * @version 1.0
 * @date 2020/12/28 13:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadDto {
    private boolean success;
    private String imgName;
}
