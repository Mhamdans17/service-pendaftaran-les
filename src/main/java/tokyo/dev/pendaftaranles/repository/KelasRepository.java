package tokyo.dev.pendaftaranles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tokyo.dev.pendaftaranles.entity.Kelas;

public interface KelasRepository extends JpaRepository<Kelas, Long> {

    boolean existsByNamaKelasAndAktifTrue(String namaKelas);
}
