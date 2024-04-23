package doomen;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "knjige")
public class Knjige {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_id_seq")
    private String isbn;
    private String naslov;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autori autor_id;

}
