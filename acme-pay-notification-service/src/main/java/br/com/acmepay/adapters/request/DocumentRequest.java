package br.com.acmepay.adapters.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentRequest implements Serializable {
    private String document;
    private String validationID;
}
