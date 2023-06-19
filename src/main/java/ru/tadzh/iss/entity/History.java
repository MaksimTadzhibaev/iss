package ru.tadzh.iss.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "history", uniqueConstraints = { @UniqueConstraint(columnNames = { "tradeDate", "secId" })})
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column
    private String tradeDate;
    @Column
    private String secId;
    @Column
    private String numTrades;
    @Column
    private String open;
    @ManyToOne
    @NotNull
    @JoinColumn(name = "securities_secId")
    Securities securities;
}
