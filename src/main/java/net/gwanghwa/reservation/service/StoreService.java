package net.gwanghwa.reservation.service;

import net.gwanghwa.reservation.entity.Member;
import net.gwanghwa.reservation.entity.Store;
import net.gwanghwa.reservation.repository.MemberRepository;
import net.gwanghwa.reservation.repository.StoreRepository;
import net.gwanghwa.reservation.type.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @packageName   : net.gwanghwa.reservation.service
* @fileName      : StoreService.java
* @author        : GwangHwa Lee
* @date          : 2024.11.13
* @description   : 매장 관련 서비스
*/
@Service
public class StoreService {

    @Autowired
    private StoreRepository storeRepository;
    
    @Autowired
    private MemberRepository memberRepository;

    /**
    * 매장 등록 처리 
    * @param name           매장 이름
    * @param location       매장 위치           
    * @param description    매장 설명
    * @param ownerId        매장 소유자 (점장)의 ID
    * @return 등록된 매장 객체
    */
    public Store registerStore(String name, String location, String description, Long ownerId) {
        // 점장 정보 가져오기
        Member owner = memberRepository.findById(ownerId).orElseThrow(() -> new IllegalArgumentException("점장 정보를 찾을 수 없습니다."));

        if(owner.getRole() != Role.ROLE_PARTNER) {
            return null;
        }
        
        // 매장 객체 생성
        Store store = new Store();
        store.setName(name);
        store.setLocation(location);
        store.setDescription(description);
        store.setOwner(owner);  // 매장 소유자는 점장

        // DB에 저장하고 반환
        return storeRepository.save(store); 
    }
}