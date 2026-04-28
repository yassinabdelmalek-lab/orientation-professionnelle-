package ma.ensi.projettutore.repository;

import ma.ensi.projettutore.entity.JobOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Integer> {
    List<JobOffer> findByUserId(Integer userId);
}