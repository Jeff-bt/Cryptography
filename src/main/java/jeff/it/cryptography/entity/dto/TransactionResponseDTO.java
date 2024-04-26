package jeff.it.cryptography.entity.dto;

import jeff.it.cryptography.entity.Transaction;

public record TransactionResponseDTO(Long id,
                                     String userDocument,
                                     String creditCardToken,
                                     Long value) {

    public static TransactionResponseDTO fromEntity(Transaction entity) {
        return new TransactionResponseDTO(
            entity.getId(),
            entity.getRawUserDocument(),
            entity.getRawCreditCardToken(),
            entity.getTransactionValue()
        );
    }

    public static TransactionResponseDTO fromEntityEncrypted(Transaction entity) {
        return new TransactionResponseDTO(
                entity.getId(),
                entity.getEncryptedUserDocument(),
                entity.getEncryptedCreditCardToken(),
                entity.getTransactionValue()
        );
    }
}
