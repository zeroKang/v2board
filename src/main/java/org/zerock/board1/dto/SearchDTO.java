package org.zerock.board1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchDTO {

    private String type;
    private String keyword;
}
