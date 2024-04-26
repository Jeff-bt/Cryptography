package jeff.it.cryptography.service;

import jeff.it.cryptography.entity.Transaction;
import jeff.it.cryptography.entity.dto.TransactionRequestDTO;
import jeff.it.cryptography.entity.dto.TransactionResponseDTO;
import jeff.it.cryptography.entity.dto.TransactionUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

public interface TransactionService {

    public TransactionResponseDTO create(TransactionRequestDTO request);
    public Page<TransactionResponseDTO> findAll(int page, int pageSize);
    public TransactionResponseDTO findById(Long id);
    public TransactionResponseDTO update(Long id, TransactionUpdateDTO request);
    public void delete(Long id);
}
