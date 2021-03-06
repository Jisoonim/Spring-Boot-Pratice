package org.zerock.day3.domain.ex1;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.zerock.day3.domain.Board;

import lombok.Data;
import lombok.ToString;

/**
 * Reply
 */
@Entity
@Table(name = "d3_reply")
@Data
@ToString(exclude = "board")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rno;

    private String reply;

    @CreationTimestamp
    private LocalDateTime replyDate;

    @ManyToOne
    private Board board;
}