package tokyo.dev.pendaftaranles.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RekapPendaftaranData {

    private String kelas;
    private long totalDaftar;
    private long totalAktif;
    private long totalBatal;
}
