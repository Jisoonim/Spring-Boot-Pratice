package org.zerock.day6.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * Reply
 */
@Entity
@Table(name = "d6_reply")
@Data
@ToString(exclude = "board")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rno;

    private String reply;

    @ManyToOne
    private Board board;

    
}