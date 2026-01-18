package it.sdc.restserver.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "vouchers")
public class Voucher {

    @Id
    private String id;

    private String code;
    private Double amount;
    private String signature;
    private Date createdAt;
}
