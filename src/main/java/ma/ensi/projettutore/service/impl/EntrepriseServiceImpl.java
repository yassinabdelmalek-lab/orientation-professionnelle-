package ma.ensi.projettutore.service.impl;

import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.entity.Entreprise;
import ma.ensi.projettutore.exception.ResourceNotFoundException;
import ma.ensi.projettutore.repository.EntrepriseRepository;
import ma.ensi.projettutore.service.EntrepriseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService {

    private final EntrepriseRepository entrepriseRepository;

    @Override
    public Entreprise createEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise updateEntreprise(Integer id, Entreprise entreprise) {
        Entreprise existing = entrepriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found with id: " + id));
        existing.setName(entreprise.getName());
        existing.setEmail(entreprise.getEmail());
        existing.setRole(entreprise.getRole());
        return entrepriseRepository.save(existing);
    }

    @Override
    public void deleteEntreprise(Integer id) {
        Entreprise existing = entrepriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found with id: " + id));
        entrepriseRepository.delete(existing);
    }

    @Override
    public Entreprise getEntrepriseById(Integer id) {
        return entrepriseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found with id: " + id));
    }

    @Override
    public Page<Entreprise> getAllEntreprises(Pageable pageable) {
        return entrepriseRepository.findAll(pageable);
    }

    @Override
    public String viewRecommendations(Integer entrepriseId) {
        entrepriseRepository.findById(entrepriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Entreprise not found with id: " + entrepriseId));
        return "Recommendations for entreprise " + entrepriseId;
    }
}