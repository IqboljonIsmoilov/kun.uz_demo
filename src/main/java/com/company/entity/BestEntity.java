package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class BestEntity extends BestTimeAndIdEntity {

    @Column(name = "name_RU", unique = true)
    private String nameRu;
    @Column(name = "name_EN", unique = true)
    private String nameEn;
    @Column(name = "name_Uz", unique = true)
    private String nameUz;
    @Column(name = "key", unique = true)
    private String key;


    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;
}