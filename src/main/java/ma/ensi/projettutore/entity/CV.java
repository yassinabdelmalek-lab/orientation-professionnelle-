package ma.ensi.projettutore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cvs")
@EqualsAndHashCode(callSuper = false)
public class CV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String filePath;

    @Column(nullable = false)
    private String extractedText;

    // Composition with User (1:1)
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // ManyToMany with Skill
    @ManyToMany
    @JoinTable(
        name = "cv_skill",
        joinColumns = @JoinColumn(name = "cv_id"),
        inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();

    public void uploadCV() {
        // Logic for uploading CV
    }

    public void extractSkills() {
        // Logic for extracting skills from CV text
    }

    public void updateCV() {
        // Logic for updating CV
    }
}