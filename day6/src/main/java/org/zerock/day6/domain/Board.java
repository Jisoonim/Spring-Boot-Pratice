package org.zerock.day6.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * Board
 */
@Entity
@Table(name = "d6_board")
@Data
@ToString(exclude = {"writer", "files"})
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bno;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;

    @OneToMany(
            mappedBy = "board", 
            fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}, //영속성 정의(부모가 영속화 될때 자식도 영속화 된다.)
            orphanRemoval = true) //고아
            
    private List<Attach> files;

    public Board() {
        this.files = new ArrayList<>();
    }
    public void addAttach(Attach attach) {//첨부파일 등록
        attach.setBoard(this);

        this.files.add(attach);
    }

    public void replaceAttach(Attach attach) {

        this.files.clear();
        attach.setBoard(this);
        this.files.add(attach);
    }

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    private List<Reply> replyList;
    
}