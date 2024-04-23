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
@Table(name = "autori")
public class Autori {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "autor_id_seq")
    private Long id;
    private String ime;


    private Integer godine;

}
