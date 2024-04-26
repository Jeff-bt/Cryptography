package jeff.it.cryptography.service.impl;

import jeff.it.cryptography.entity.Transaction;
import jeff.it.cryptography.entity.dto.TransactionRequestDTO;
import jeff.it.cryptography.entity.dto.TransactionResponseDTO;
import jeff.it.cryptography.entity.dto.TransactionUpdateDTO;
import jeff.it.cryptography.repository.TransactionRepository;
import jeff.it.cryptography.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public TransactionResponseDTO create(TransactionRequestDTO request) {
        var entity = new Transaction();
        entity.setRawUserDocument(request.userDocument());
        entity.setRawCreditCardToken((request.creditCardToken()));
        entity.setTransactionValue(request.value());

        var entityDb = transactionRepository.save(entity);
        return TransactionResponseDTO.fromEntityEncrypted(entityDb);
    }

    @Override
    public Page<TransactionResponseDTO> findAll(int page, int pageSize) {
        var content = transactionRepository.findAll(PageRequest.of(page, pageSize));
        return content.map(TransactionResponseDTO::fromEntity);
    }

    @Override
    public TransactionResponseDTO findById(Long id) {
        var entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return TransactionResponseDTO.fromEntity(entity);
    }

    @Override
    public TransactionResponseDTO update(Long id, TransactionUpdateDTO request) {
        var entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        entity.setTransactionValue(request.value());

        var entityDb = transactionRepository.save(entity);
        return TransactionResponseDTO.fromEntityEncrypted(entityDb);
    }

    @Override
    public void delete(Long id) {
        var entity = transactionRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        transactionRepository.deleteById(id);
    }
}
