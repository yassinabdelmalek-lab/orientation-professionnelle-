package ma.ensi.projettutore.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.ensi.projettutore.entity.Entreprise;
import ma.ensi.projettutore.service.EntrepriseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entreprises")
@RequiredArgsConstructor
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    @PostMapping
    public ResponseEntity<Entreprise> createEntreprise(@Valid @RequestBody Entreprise entreprise) {
        return ResponseEntity.ok(entrepriseService.createEntreprise(entreprise));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entreprise> updateEntreprise(@PathVariable Integer id, @Valid @RequestBody Entreprise entreprise) {
        return ResponseEntity.ok(entrepriseService.updateEntreprise(id, entreprise));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntreprise(@PathVariable Integer id) {
        entrepriseService.deleteEntreprise(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable Integer id) {
        return ResponseEntity.ok(entrepriseService.getEntrepriseById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Entreprise>> getAllEntreprises(Pageable pageable) {
        return ResponseEntity.ok(entrepriseService.getAllEntreprises(pageable));
    }

    @GetMapping("/{entrepriseId}/recommendations")
    public ResponseEntity<String> viewRecommendations(@PathVariable Integer entrepriseId) {
        return ResponseEntity.ok(entrepriseService.viewRecommendations(entrepriseId));
    }
}