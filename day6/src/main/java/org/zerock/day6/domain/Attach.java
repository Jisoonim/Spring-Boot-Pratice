package org.zerock.day6.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * Attach
 */
@Entity
@Table(name = "d6_attach")
@Data
@ToString(exclude = "board")
public class Attach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ano; //첨부파일 번호

    private String fname; //파일 이름

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private Boolean current;
}