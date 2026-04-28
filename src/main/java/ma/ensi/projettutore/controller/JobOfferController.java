package ma.ensi.projettutore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.dto.request.JobOfferRequest;
import ma.ensi.projettutore.dto.response.JobOfferResponse;
import ma.ensi.projettutore.entity.JobOffer;
import ma.ensi.projettutore.service.JobOfferService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-offers")
@RequiredArgsConstructor
@CrossOrigin
public class JobOfferController {

    private final JobOfferService jobOfferService;

    @PostMapping
    public ResponseEntity<JobOffer> createJobOffer(@Valid @RequestBody JobOffer jobOffer) {
        return ResponseEntity.ok(jobOfferService.createJobOffer(jobOffer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobOffer> updateJobOffer(@PathVariable Integer id, @Valid @RequestBody JobOffer jobOffer) {
        return ResponseEntity.ok(jobOfferService.updateJobOffer(id, jobOffer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobOffer(@PathVariable Integer id) {
        jobOfferService.deleteJobOffer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOffer> getJobOfferById(@PathVariable Integer id) {
        return ResponseEntity.ok(jobOfferService.getJobOfferById(id));
    }

    @GetMapping
    public ResponseEntity<Page<JobOffer>> getAllJobOffers(Pageable pageable) {
        return ResponseEntity.ok(jobOfferService.getAllJobOffers(pageable));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JobOffer>> getJobOffersByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(jobOfferService.getJobOffersByUser(userId));
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<JobOfferResponse> createJob(@PathVariable Integer userId, @Valid @RequestBody JobOfferRequest request) {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setTitre(request.getTitre());
        jobOffer.setDescription(request.getDescription());
        jobOffer.setCategory(request.getCategory());
        return ResponseEntity.ok(jobOfferService.createJob(userId, jobOffer));
    }

    @PutMapping("/user/{userId}/{jobId}")
    public ResponseEntity<JobOfferResponse> updateJob(@PathVariable Integer userId, @PathVariable Integer jobId, @Valid @RequestBody JobOfferRequest request) {
        JobOffer jobOffer = new JobOffer();
        jobOffer.setTitre(request.getTitre());
        jobOffer.setDescription(request.getDescription());
        jobOffer.setCategory(request.getCategory());
        return ResponseEntity.ok(jobOfferService.updateJob(userId, jobId, jobOffer));
    }

    @DeleteMapping("/user/{userId}/{jobId}")
    public ResponseEntity<Void> deleteJob(@PathVariable Integer userId, @PathVariable Integer jobId) {
        jobOfferService.deleteJob(userId, jobId);
        return ResponseEntity.ok().build();
    }
}