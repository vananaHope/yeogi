package com.vanana.yeogi.base.entity.auth;

import com.vanana.yeogi.base.entity.common.BaseEt;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEt extends BaseEt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "nickname")
    private String nickName;

    @Column(name = "gender")
    private String gender;

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<UserTerms> userTerms = new ArrayList<>();

    @Builder
    public UserEt(String email, String password, String phoneNumber, LocalDate birth, String nickName, String gender){
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.nickName = nickName;
        this.gender = gender;
    }
}
