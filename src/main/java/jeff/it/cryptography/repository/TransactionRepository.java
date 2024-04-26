package jeff.it.cryptography.repository;

import jeff.it.cryptography.entity.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
   // Page<Transaction> findAll(int page, int pageSize);
}
