package com.sit.jbc.domain.entity.security;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by User on 24-Dec-18.
 */
@Getter
@Setter

@Entity
@Table(name ="TEST_DB_FIRST" )
public class Test {
    @Id
    @SequenceGenerator(name="pk_seq_testdb",sequenceName="testdb_seq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pk_seq_testdb")
    @Column(name = "TEST_ID")
    private Long testDbId;
    @Column(name ="TEST_NAME")
    private String testName;
}
