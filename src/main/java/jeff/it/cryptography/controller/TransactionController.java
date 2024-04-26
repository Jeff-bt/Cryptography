package jeff.it.cryptography.controller;

import jeff.it.cryptography.entity.dto.TransactionRequestDTO;
import jeff.it.cryptography.entity.dto.TransactionResponseDTO;
import jeff.it.cryptography.entity.dto.TransactionUpdateDTO;
import jeff.it.cryptography.service.TransactionService;
import jeff.it.cryptography.service.impl.TransactionServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create (@RequestBody TransactionRequestDTO request){
        var body = transactionService.create(request);
        return ResponseEntity.ok(body);
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponseDTO>> findAll (@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){
        var body = transactionService.findAll(page, pageSize);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> findById (@PathVariable(value = "id") Long id){
        var body = transactionService.findById(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> update (@PathVariable(value = "id") Long id,
                                                          @RequestBody TransactionUpdateDTO request){
        var body = transactionService.update(id, request);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable(value = "id") Long id){
        transactionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
