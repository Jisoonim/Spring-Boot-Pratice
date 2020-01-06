package org.zerock.day7.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Board
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Board {

    private Integer bno;
    private String title;
    private String content;
    private String writer;
}