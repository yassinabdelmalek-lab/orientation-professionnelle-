package ma.ensi.projettutore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.dto.response.JobOfferResponse;
import ma.ensi.projettutore.entity.JobOffer;
import ma.ensi.projettutore.entity.User;
import ma.ensi.projettutore.exception.ResourceNotFoundException;
import ma.ensi.projettutore.repository.JobOfferRepository;
import ma.ensi.projettutore.repository.UserRepository;
import ma.ensi.projettutore.service.JobOfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JobOfferServiceImpl implements JobOfferService {

    private final JobOfferRepository jobOfferRepository;
    private final UserRepository userRepository;

    @Override
    public JobOffer createJobOffer(JobOffer jobOffer) {
        return jobOfferRepository.save(jobOffer);
    }

    @Override
    public JobOffer updateJobOffer(Integer id, JobOffer jobOffer) {
        JobOffer existing = jobOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobOffer not found with id: " + id));
        existing.setTitre(jobOffer.getTitre());
        existing.setDescription(jobOffer.getDescription());
        existing.setCategory(jobOffer.getCategory());
        return jobOfferRepository.save(existing);
    }

    @Override
    public void deleteJobOffer(Integer id) {
        JobOffer existing = jobOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobOffer not found with id: " + id));
        jobOfferRepository.delete(existing);
    }

    @Override
    public JobOffer getJobOfferById(Integer id) {
        return jobOfferRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("JobOffer not found with id: " + id));
    }

    @Override
    public Page<JobOffer> getAllJobOffers(Pageable pageable) {
        return jobOfferRepository.findAll(pageable);
    }

    @Override
    public List<JobOffer> getJobOffersByUser(Integer userId) {
        return jobOfferRepository.findByUserId(userId);
    }

    @Override
    public JobOfferResponse createJob(Integer userId, JobOffer jobOffer) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
        jobOffer.setUser(user);
        JobOffer saved = jobOfferRepository.save(jobOffer);
        return toResponse(saved);
    }

    @Override
    public JobOfferResponse updateJob(Integer userId, Integer jobId, JobOffer jobOffer) {
        JobOffer existing = jobOfferRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("JobOffer not found with id: " + jobId));
        existing.setTitre(jobOffer.getTitre());
        existing.setDescription(jobOffer.getDescription());
        existing.setCategory(jobOffer.getCategory());
        return toResponse(jobOfferRepository.save(existing));
    }

    @Override
    public void deleteJob(Integer userId, Integer jobId) {
        JobOffer existing = jobOfferRepository.findById(jobId)
                .orElseThrow(() -> new ResourceNotFoundException("JobOffer not found with id: " + jobId));
        jobOfferRepository.delete(existing);
    }

    private JobOfferResponse toResponse(JobOffer jobOffer) {
        return JobOfferResponse.builder()
                .id(jobOffer.getId())
                .titre(jobOffer.getTitre())
                .description(jobOffer.getDescription())
                .category(jobOffer.getCategory())
                .userName(jobOffer.getUser() != null ? jobOffer.getUser().getName() : null)
                .build();
    }
}