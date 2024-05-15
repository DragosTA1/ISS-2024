package ro.iss2024.repository.irepository;

import ro.iss2024.domain.Admin;
import ro.iss2024.repository.IRepository;

import java.util.Optional;

public interface IAdminRepo extends IRepository<Long, Admin> {
    Optional<Admin> findByUsername(String username);
}
