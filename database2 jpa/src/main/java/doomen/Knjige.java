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
    private String isbn;
    private String naslov;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id", referencedColumnName = "id")
    private Autori autor;

}
