package tokyo.dev.pendaftaranles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "kelas")
@Getter
@Setter
public class Kelas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_kelas", nullable = false, unique = true)
    private String namaKelas;

    @Column(nullable = false)
    private Boolean aktif = true;

}
