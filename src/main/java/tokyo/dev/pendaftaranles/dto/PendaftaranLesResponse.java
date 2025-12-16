package tokyo.dev.pendaftaranles.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PendaftaranLesResponse {

    private Long id;
    private String namaSiswa;
    private String kelas;
    private String jenisLes;
    private String status;
}
