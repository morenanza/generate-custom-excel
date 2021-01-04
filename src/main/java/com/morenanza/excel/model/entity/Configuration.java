package com.morenanza.excel.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "configuration")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Configuration {
    @Id
    @Column(name = "key")
    private String key;

    @Column(name = "value")
    private String value;
}
