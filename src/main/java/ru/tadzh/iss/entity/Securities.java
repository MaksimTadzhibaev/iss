package ru.tadzh.iss.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "securities")
public class Securities {

    @Id
    @Column
    private String secId;
    @Column
    private String regNumber;
    @Column
    private String name;
    @Column
    private String emitentTitle;
}
