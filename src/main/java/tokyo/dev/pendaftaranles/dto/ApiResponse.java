package tokyo.dev.pendaftaranles.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse<T> {

    private String status;
    private String responseCode;
    private String responseMessage;
    private T data;
}
