package com.example.sample1.repository;

import com.example.sample1.entity.ItemEntity;
import com.example.sample1.entity.QItemEntity;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository; //객체 주입해야 ItemRepository가 상속받은 여러 메서드 사용할 수 있음

    @Test //org.junit.jupiter.api.Test 클래스
    @DisplayName("10번 레코드 삽입")
    public void itemSave() {
        String[] fruit = {"사과", "배", "오렌지"};
        String[] detail = {"달아요", "맛있어요", "맛없어요", "떫어요"};
        int[] price = {111, 222, 333, 444, 555};

        for(int i=1;i<=10;i++) {
            ItemEntity item = new ItemEntity();
            item.setItemNm(fruit[i% fruit.length]);
            item.setPrice(price[i% price.length]);
            item.setItemDetail(detail[i% detail.length]);
            item.setRegTime(LocalDateTime.now());

            ItemEntity ie = itemRepository.save(item);
            System.out.println("ie : " + ie);

        }
    }

    @Test
    @DisplayName("2. 모든 상품 조회")
    public void selectAll() {
        List<ItemEntity> itemList = itemRepository.findAll();
        for(ItemEntity ie : itemList) {
            System.out.println("ie : " + ie);
        }
    }

    @Test
    @DisplayName("3. 오렌지 상품 조회")
    public void findByItemNm() {
       List<ItemEntity> itemList = itemRepository.findByItemNm("오렌지");
       for(ItemEntity ie : itemList) {
           System.out.println("ie : " + ie);
       }
    }

    @Test
    @DisplayName("4. 3번 상품 조회")
    public void findId() {
        Optional<ItemEntity> ie = itemRepository.findById(3L);
        System.out.println("ie : " + ie);
    }

    @Test
    @DisplayName("5. 가격이 300 미만인 레코드 조회")
    public void price300() {
        List<ItemEntity> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(300);
        for(ItemEntity ie : itemList) {
            System.out.println("ie : " + ie);
        }
    }

    @Test
    @DisplayName("6. 전체 레코드 조회")
    public void selectAll2() {
        List<ItemEntity> itemList = itemRepository.findAllQuery();
        for(ItemEntity ie : itemList) {
            System.out.println("ie : " + ie);
        }
    }

    @Test
    @DisplayName("7. 전체 레코드 개수 조회")
    public void findAllCount() {
        Integer count = itemRepository.findAllCountQuery();
        System.out.println("count : " + count);
    }

    @Test
    @DisplayName("8. 상품 설명에 어 포함된 레코드 개수 조회")
    public void findAllCount2() {
        Integer count2 = itemRepository.findAllCountQuery2("어");
        System.out.println("count2 : " + count2);
    }

    @Test
    @DisplayName("9. 상품 설명에 어 포함된 레코드 조회")
    public void selectAllWithItemDetailText() {
        List<ItemEntity> itemList = itemRepository.selectAllWithItemDetailText("어");
        for(ItemEntity ie : itemList) {
            System.out.println("ie : " + ie);
        }
    }


    //QueryDSL
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("10. 조회")
    public void findItemEntity() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        QItemEntity qItem = QItemEntity.itemEntity;
        JPAQuery<ItemEntity> query = queryFactory
                .selectFrom(qItem)
                .where(qItem.itemDetail.like("%어%").and(qItem.id.gt(5L)))
                .where(qItem.price.between(200, 400))
                .orderBy(qItem.price.desc());
        List<ItemEntity> itemList = query.fetch();
        for(ItemEntity ie : itemList) {
            System.out.println("ie : " + ie);
        }
    }


}
/*
Q클래스 생성 방법
project structure > module > sources > target/generate-sources > sources > maven clean, compile >
한번 더 module > source > target/generate-sources > sources 2번 클릭 > maven clean > main 실행 > import
*/
