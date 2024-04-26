package jeff.it.cryptography.entity.dto;

public record TransactionRequestDTO(String userDocument,
                                    String creditCardToken,
                                    Long value) {
}
