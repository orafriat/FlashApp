package com.flash.app.dao;

import com.flash.app.enitity.RequestPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface RequestPaymentDAO extends JpaRepository<RequestPayment, Long> {

    public RequestPayment findById(@Param("id") Integer id);


//    @Query(value = "select * from hotels r where lower(r.hotel_name) like lower(concat('%', :hotel_name,'%'))", nativeQuery = true)
  //  public List<RequestPayment> findByFromUserId(@Param("hotel_name")String course);

    public List<RequestPayment> findByFromUserId(@Param("FromUserId") Integer FromUserId);

    public List<RequestPayment> findByToUserId(@Param("ToUserId") Integer ToUserId);

    public List<RequestPayment> findByRequestCode(@Param("RequestCode") Integer RequestCode);

    @Override
    List<RequestPayment> findAll();
}