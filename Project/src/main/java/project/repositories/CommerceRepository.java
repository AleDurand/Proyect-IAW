package project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.model.Commerce;

@Repository
public interface CommerceRepository extends JpaRepository<Commerce, Long> {

	public Commerce findById( Long id );

}