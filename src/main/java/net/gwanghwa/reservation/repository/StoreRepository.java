package net.gwanghwa.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.gwanghwa.reservation.entity.Store;

/**
* @packageName   : net.gwanghwa.reservation.repository
* @fileName      : StoreRepository.java
* @author        : GwangHwa Lee
* @date          : 2024.11.13
* @description   : 매장 정보를 처리하는 리포지토리
*/
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {

    /**
    * 매장 이름으로 매장 검색
    * @param name
    * @return 매장 객체
    */
    Store findByName(String name);
}
