package ro.iss2024.repository.irepository;

import ro.iss2024.domain.Spectator;
import ro.iss2024.repository.IRepository;

import java.util.Optional;

public interface ISpectatorRepo extends IRepository<Long, Spectator> {

    Optional<Spectator> findByUsername(String username);

}
