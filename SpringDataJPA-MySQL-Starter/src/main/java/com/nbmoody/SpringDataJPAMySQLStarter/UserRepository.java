package com.nbmoody.SpringDataJPAMySQLStarter;

import org.springframework.data.repository.CrudRepository;
import com.nbmoody.SpringDataJPAMySQLStarter.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
public interface UserRepository extends CrudRepository<User, Integer> {


}
