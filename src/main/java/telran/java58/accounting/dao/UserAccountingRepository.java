package telran.java58.accounting.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import telran.java58.accounting.model.UserAccount;

public interface UserAccountingRepository extends MongoRepository<UserAccount, String> {
}
