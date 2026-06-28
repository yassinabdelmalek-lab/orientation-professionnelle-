package ma.ensi.projettutore.service;

import ma.ensi.projettutore.entity.Entreprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntrepriseService {
    Entreprise createEntreprise(Entreprise entreprise);
    Entreprise updateEntreprise(Integer id, Entreprise entreprise);
    void deleteEntreprise(Integer id);
    Entreprise getEntrepriseById(Integer id);
    Page<Entreprise> getAllEntreprises(Pageable pageable);
    String viewRecommendations(Integer entrepriseId);
}