package com.icia.member.entity;

import com.icia.member.dto.MemberDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "member_table")
public class MemberEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = (200),nullable = false)
    private String memberEmail;

    @Column(length = (200),nullable = false)
    private String memberPassword;

    @Column(length = (200),nullable = false)
    private String memberName;

    @Column(length = (200),nullable = false)
    private String memberMobile;

    @Column(length = (200),nullable = false)
    private String memberBirth;

    @Column
    private int fileAttached;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id") // DB에 생성될 참조 컬럼의 이름
    private BoardEntity boardEntity; // 부모 엔티티 타입으로 정의



    public static MemberEntity toSaveEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(memberDTO.getMemberEmail());
        memberEntity.setMemberPassword(memberDTO.getMemberPassword());
        memberEntity.setMemberName(memberDTO.getMemberName());
        memberEntity.setMemberMobile(memberDTO.getMemberMobile());
        memberEntity.setMemberBirth(memberDTO.getMemberBirth());
        memberEntity.setFileAttached(0);
        return memberEntity;
    }

}








