package dev.feryadi.backend.bayu.entity;

import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mutations")
@SQLDelete(sql = "UPDATE wallets SET deleted = true WHERE id = ?")
@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
public class Mutation extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "initial_balance")
    private BigDecimal initialBalance;

    @Column(name = "balance")
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "ENUM('DEBIT','CREDIT')")
    private MutationType type;

    @ManyToOne
    @JoinColumn(name = "sender_wallet_id", referencedColumnName = "id")
    private Wallet senderWallet;

    @ManyToOne
    @JoinColumn(name = "receiver_wallet_id", referencedColumnName = "id")
    private Wallet receiverWallet;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    public enum MutationType {
        DEBIT, CREDIT
    }
}
