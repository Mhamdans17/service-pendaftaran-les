package tokyo.dev.pendaftaranles.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pendaftaran_les")
@Getter
@Setter
public class PendaftaranLes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama_siswa")
    private String namaSiswa;

    private String kelas;

    @Column(name = "jenis_les")
    private String jenisLes;

    private String status;
}
