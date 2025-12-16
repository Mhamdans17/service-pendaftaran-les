package tokyo.dev.pendaftaranles.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RekapPendaftaranApiResponse {
    private String status;
    private String responseCode;
    private String responseMessage;

    private String kelas;
    private long totalDaftar;
    private long totalAktif;
    private long totalBatal;
}
