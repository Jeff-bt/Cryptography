package jeff.it.cryptography.entity;

import jakarta.persistence.*;
import jeff.it.cryptography.service.CryptoService;
import lombok.Data;

@Data
@Entity
@Table(name = "tb_transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_document")
    private String encryptedUserDocument;

    @Column(name = "credit_card_token")
    private String encryptedCreditCardToken;

    @Column(name = "transaction_value")
    private long transactionValue;

    @Transient //Serve para não persistir no banco de dados, ou seja não transformar em uma column in db
    private String rawUserDocument;

    @Transient
    private String rawCreditCardToken;

    @PrePersist //Antes de persisitir os dados, esse método é executado.
    public void prePersist(){
        this.encryptedUserDocument = CryptoService.encrypt(rawUserDocument);
        this.encryptedCreditCardToken = CryptoService.encrypt(rawCreditCardToken);
    }

    @PostLoad //Após ler dados do db, esse método é executado.
    public void postLoad(){
        this.rawUserDocument = CryptoService.decrypt(encryptedUserDocument);
        this.rawCreditCardToken = CryptoService.decrypt(encryptedCreditCardToken);
    }


}
