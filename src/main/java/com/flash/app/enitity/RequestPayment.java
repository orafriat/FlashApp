package com.flash.app.enitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "request_payment")
public class RequestPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //This is the title of the recipe
    @Column(name = "creationDate")
    private String creationDate;

    @Column(name = "fromUserId")
    private Integer fromUserId;

    @Column(name = "toUserId")
    private Integer toUserId;

    @Column(name = "requestCode")
    private Integer requestCode;

    @Column(name = "status")
    private String status;

    @Column(name = "requestApproveDate")
    private String requestApproveDate;

    @Column(name = "requestAmount")
    private double requestAmount;


}
