package tokyo.dev.pendaftaranles.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tokyo.dev.pendaftaranles.entity.PendaftaranLes;

import java.util.List;

@Repository
public interface PendaftaranLesRepository extends JpaRepository<PendaftaranLes, Long> {

    @Query(
            value = """
            SELECT
                id,
                nama_siswa,
                jenis_les,
                kelas,
                status
            FROM pendaftaran_les
            WHERE kelas = :kelas
            """,
            nativeQuery = true
    )
    List<PendaftaranLes> findByKelas(@Param("kelas") String kelas);
}
