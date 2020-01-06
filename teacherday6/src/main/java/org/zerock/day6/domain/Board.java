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
@Table(name="d6_board")
@Data
@ToString(exclude = {"writer","files"})
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
        cascade = {CascadeType.PERSIST, CascadeType.MERGE},
        orphanRemoval = true )
    private List<Attach> files;

    public Board(){
        this.files = new ArrayList<>();
    }
    public void addAttach(Attach attach){
        attach.setBoard(this);
        this.files.add(attach);
    }

    public void replaceAttach(Attach attach){

        this.files.clear();
        attach.setBoard(this);
        this.files.add(attach);

    }


}









