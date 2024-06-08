package br.com.acmepay.adapters.request;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class DocumentRequest implements Serializable {
    private String document;
}
