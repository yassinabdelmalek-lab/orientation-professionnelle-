package ma.ensi.projettutore.service;

import ma.ensi.projettutore.dto.response.JobOfferResponse;
import ma.ensi.projettutore.entity.JobOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobOfferService {
    JobOffer createJobOffer(JobOffer jobOffer);
    JobOffer updateJobOffer(Integer id, JobOffer jobOffer);
    void deleteJobOffer(Integer id);
    JobOffer getJobOfferById(Integer id);
    Page<JobOffer> getAllJobOffers(Pageable pageable);
    List<JobOffer> getJobOffersByUser(Integer userId);
    JobOfferResponse createJob(Integer userId, JobOffer jobOffer);
    JobOfferResponse updateJob(Integer userId, Integer jobId, JobOffer jobOffer);
    void deleteJob(Integer userId, Integer jobId);
}