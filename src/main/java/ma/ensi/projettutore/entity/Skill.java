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
@Table(name = "skills")
@EqualsAndHashCode(callSuper = false)
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String name;

    // ManyToMany with CV
    @ManyToMany(mappedBy = "skills")
    private Set<CV> cvs = new HashSet<>();

    public String addSkill() {
        // Logic for adding skill
        return "Skill added";
    }

    public String removeSkill() {
        // Logic for removing skill
        return "Skill removed";
    }
}