package br.com.acmepay.adapters.input.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CustomerListResponse {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String document;
}
