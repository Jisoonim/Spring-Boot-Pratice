package org.zerock.day6.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * Member
 */
@Entity
@Table(name="d6_member")
@Data
public class Member {

    @Id
    private String mid;
    private String mpw;
    private String mname;
    private String nickname;
    
}