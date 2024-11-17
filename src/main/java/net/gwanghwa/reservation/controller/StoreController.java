package net.gwanghwa.reservation.controller;

import net.gwanghwa.reservation.service.StoreService;
import net.gwanghwa.reservation.entity.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
* @packageName   : net.gwanghwa.reservation.controller
* @fileName      : StoreController.java
* @author        : GwangHwa Lee
* @date          : 2024.11.13
* @description   : 매장 관리와 관련된 API를 처리하는 컨트롤러
*/
@RestController
@RequestMapping("/api/stores")
public class StoreController {

    @Autowired
    private StoreService storeService;

    /**
    * 매장 등록 API
    * @param name           매장 이름
    * @param location       매장 위치
    * @param description    매장 설명
    * @param ownerId        매장 소유자(점장)의 ID
    * @return 등록된 매장 객체
    */
    @PostMapping("/register")
    public ResponseEntity<Store> registerStore(@RequestParam(value="name") String name,
                                @RequestParam(value="location") String location,
                                @RequestParam(value="description") String description,
                                @RequestParam(value="ownerId")  Long ownerId) {
        Store store = storeService.registerStore(name, location, description, ownerId);
        
        if (store == null) {
            return ResponseEntity.status(401).body(store);
        }
        
        
        
        return ResponseEntity.ok(store);
    }
}