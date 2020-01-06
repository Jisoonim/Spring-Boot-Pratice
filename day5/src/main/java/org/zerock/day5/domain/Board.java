package org.zerock.day5.domain;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
import lombok.ToString;

/**
 * Board
 */
@Entity
@Table(name = "d3_board")
@Data
@ToString(exclude = "replyList")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;
    private String title;
    private String content;

    @CreationTimestamp
    private LocalDateTime regDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "board")   // mappedBy = 상대방한테 어떤 변수명으로 매핑되어있나 입력해주면 컬럼추가하는 방식으로 해줌
    @JsonIgnore
    private List<Reply> replyList;

    public Reply mackReply(){
        Reply reply = new Reply();
        reply.setBoard(this);
        return reply;
    }
}