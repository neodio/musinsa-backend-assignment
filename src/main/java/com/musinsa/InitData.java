package com.musinsa;

import com.musinsa.domain.brand.entity.Brand;
import com.musinsa.domain.category.entity.Category;
import com.musinsa.domain.product.entity.Product;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class InitData {

    private final InitDataSetting initDataSetting;


    @PostConstruct
    public void init() {
        initDataSetting.init();
    }

    @Component
    static class InitDataSetting {
        @PersistenceContext
        EntityManager em;

        @Transactional
        public void init() {
            // category
            Category categoryTop = new Category("상의");
            Category categoryOuter = new Category("아우터");
            Category categoryPants = new Category("바지");
            Category categorySneakers = new Category("스니커즈");
            Category categoryBag = new Category("가방");
            Category categoryCap = new Category("모자");
            Category categorySocks = new Category("양말");
            Category categoryAccessory = new Category("액세서리");

            em.persist(categoryTop);
            em.persist(categoryOuter);
            em.persist(categoryPants);
            em.persist(categorySneakers);
            em.persist(categoryBag);
            em.persist(categoryCap);
            em.persist(categorySocks);
            em.persist(categoryAccessory);

            // brand
            Brand brandA = new Brand("A");
            Brand brandB = new Brand("B");
            Brand brandC = new Brand("C");
            Brand brandD = new Brand("D");
            Brand brandE = new Brand("E");
            Brand brandF = new Brand("F");
            Brand brandG = new Brand("G");
            Brand brandH = new Brand("H");
            Brand brandI = new Brand("I");

            em.persist(brandA);
            em.persist(brandB);
            em.persist(brandC);
            em.persist(brandD);
            em.persist(brandE);
            em.persist(brandF);
            em.persist(brandG);
            em.persist(brandH);
            em.persist(brandI);

            // product
            em.persist(new Product("상의1", 11200, categoryTop, brandA));
            em.persist(new Product("상의2", 10500, categoryTop, brandB));
            em.persist(new Product("상의3", 10000, categoryTop, brandC));
            em.persist(new Product("상의4", 10100, categoryTop, brandD));
            em.persist(new Product("상의5", 10700, categoryTop, brandE));
            em.persist(new Product("상의6", 11200, categoryTop, brandF));
            em.persist(new Product("상의7", 10500, categoryTop, brandG));
            em.persist(new Product("상의8", 10800, categoryTop, brandH));
            em.persist(new Product("상의9", 11400, categoryTop, brandI));

            em.persist(new Product("아우터1", 5500, categoryOuter, brandA));
            em.persist(new Product("아우터2", 5900, categoryOuter, brandB));
            em.persist(new Product("아우터3", 6200, categoryOuter, brandC));
            em.persist(new Product("아우터4", 5100, categoryOuter, brandD));
            em.persist(new Product("아우터5", 5000, categoryOuter, brandE));
            em.persist(new Product("아우터6", 7200, categoryOuter, brandF));
            em.persist(new Product("아우터7", 5800, categoryOuter, brandG));
            em.persist(new Product("아우터8", 6300, categoryOuter, brandH));
            em.persist(new Product("아우터9", 6700, categoryOuter, brandI));

            em.persist(new Product("바지1", 4200, categoryPants, brandA));
            em.persist(new Product("바지2", 3800, categoryPants, brandB));
            em.persist(new Product("바지3", 3300, categoryPants, brandC));
            em.persist(new Product("바지4", 3000, categoryPants, brandD));
            em.persist(new Product("바지5", 3800, categoryPants, brandE));
            em.persist(new Product("바지6", 4000, categoryPants, brandF));
            em.persist(new Product("바지7", 3900, categoryPants, brandG));
            em.persist(new Product("바지8", 3100, categoryPants, brandH));
            em.persist(new Product("바지9", 3200, categoryPants, brandI));

            em.persist(new Product("스니커즈1", 9000, categorySneakers, brandA));
            em.persist(new Product("스니커즈2", 9100, categorySneakers, brandB));
            em.persist(new Product("스니커즈3", 9200, categorySneakers, brandC));
            em.persist(new Product("스니커즈4", 9500, categorySneakers, brandD));
            em.persist(new Product("스니커즈5", 9900, categorySneakers, brandE));
            em.persist(new Product("스니커즈6", 9300, categorySneakers, brandF));
            em.persist(new Product("스니커즈7", 9000, categorySneakers, brandG));
            em.persist(new Product("스니커즈8", 9700, categorySneakers, brandH));
            em.persist(new Product("스니커즈9", 9500, categorySneakers, brandI));

            em.persist(new Product("가방1", 2000, categoryBag, brandA));
            em.persist(new Product("가방2", 2100, categoryBag, brandB));
            em.persist(new Product("가방3", 2200, categoryBag, brandC));
            em.persist(new Product("가방4", 2500, categoryBag, brandD));
            em.persist(new Product("가방5", 2300, categoryBag, brandE));
            em.persist(new Product("가방6", 2100, categoryBag, brandF));
            em.persist(new Product("가방7", 2200, categoryBag, brandG));
            em.persist(new Product("가방8", 2100, categoryBag, brandH));
            em.persist(new Product("가방9", 2400, categoryBag, brandI));

            em.persist(new Product("모자1", 1700, categoryCap, brandA));
            em.persist(new Product("모자2", 2000, categoryCap, brandB));
            em.persist(new Product("모자3", 1900, categoryCap, brandC));
            em.persist(new Product("모자4", 1500, categoryCap, brandD));
            em.persist(new Product("모자5", 1800, categoryCap, brandE));
            em.persist(new Product("모자6", 1600, categoryCap, brandF));
            em.persist(new Product("모자7", 1700, categoryCap, brandG));
            em.persist(new Product("모자8", 1600, categoryCap, brandH));
            em.persist(new Product("모자9", 1700, categoryCap, brandI));

            em.persist(new Product("양말1", 1800, categorySocks, brandA));
            em.persist(new Product("양말2", 2000, categorySocks, brandB));
            em.persist(new Product("양말3", 2200, categorySocks, brandC));
            em.persist(new Product("양말4", 2400, categorySocks, brandD));
            em.persist(new Product("양말5", 2100, categorySocks, brandE));
            em.persist(new Product("양말6", 2300, categorySocks, brandF));
            em.persist(new Product("양말7", 2100, categorySocks, brandG));
            em.persist(new Product("양말8", 2000, categorySocks, brandH));
            em.persist(new Product("양말9", 1700, categorySocks, brandI));

            em.persist(new Product("액세서리1", 2300, categoryAccessory, brandA));
            em.persist(new Product("액세서리2", 2200, categoryAccessory, brandB));
            em.persist(new Product("액세서리3", 2100, categoryAccessory, brandC));
            em.persist(new Product("액세서리4", 2000, categoryAccessory, brandD));
            em.persist(new Product("액세서리5", 2100, categoryAccessory, brandE));
            em.persist(new Product("액세서리6", 1900, categoryAccessory, brandF));
            em.persist(new Product("액세서리7", 2000, categoryAccessory, brandG));
            em.persist(new Product("액세서리8", 2000, categoryAccessory, brandH));
            em.persist(new Product("액세서리9", 2400, categoryAccessory, brandI));
        }
    }
}
