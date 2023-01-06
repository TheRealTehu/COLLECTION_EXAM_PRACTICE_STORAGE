package org.example;

import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StorageTest {
    private List<Product> products = new ArrayList<>();

    private Storage storage;

    private Product product1 = new Product("Tej", 200, LocalDate.now().plusDays(100),
            new HashSet<>(){{add("tejtermék"); add("ital"); add("főzés");}});
    private Product product2 = new Product("Tej", 200, LocalDate.now().plusDays(1),
            new HashSet<>(){{add("tejtermék"); add("ital"); add("főzés");}});
    private Product product3 = new Product("Tej", 200, LocalDate.now().minusDays(3),
            new HashSet<>(){{add("tejtermék"); add("ital"); add("főzés");}});
    private Product product4 = new Product("Tej", 200, LocalDate.now(),
            new HashSet<>(){{add("tejtermék"); add("ital"); add("főzés");}});
    private Product product5 = new Product("Tej", 200, LocalDate.now(),
            new HashSet<>(){{add("tejtermék"); add("ital"); add("főzés");}});
    private Product product6 = new Product("Kóla", 300, LocalDate.now().plusDays(100),
            new HashSet<>(){{add("üdítő"); add("ital"); add("cukros");}});
    private Product product7 = new Product("Kóla", 300, LocalDate.now().plusDays(100),
            new HashSet<>(){{add("üdítő"); add("ital"); add("cukros");}});
    private Product product8 = new Product("Kóla", 300, LocalDate.now().minusDays(30),
            new HashSet<>(){{add("üdítő"); add("ital"); add("cukros");}});
    private Product product9 = new Product("Liszt", 100, LocalDate.now().minusDays(2),
            new HashSet<>(){{add("főzés");}});
    private Product product10 = new Product("Liszt", 100, LocalDate.now(),
            new HashSet<>(){{add("főzés");}});

    @BeforeEach()
    public void setup(){
        //Lejárt termékek: 3 | Aznap lejáró termékek: 3 | lejárt termékek összege: 600
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);
        products.add(product6);
        products.add(product7);
        products.add(product8);
        products.add(product9);
        products.add(product10);

        storage = new Storage(products);
    }

    @Test
    @Order(1)
    void testIfExceptionIsThrown(){
        assertThrows(IllegalArgumentException.class, ()->new Storage(null));
    }

    @Test
    @Order(2)
    void testIfProgramCanFindExpirationsOnTheDayOf(){
        assertThat(storage.findProductsOnlyValidToday())
                .isNotEmpty()
                .hasSize(3)
                .contains(product4, product5, product10);
    }

    @Test
    @Order(3)
    void testIfProgramCanFindAllProductNamesInGivenCategory(){
        assertThat(storage.getProductNamesInCategory("főzés"))
                .isNotEmpty()
                .hasSize(2)
                .contains("Tej", "Liszt");
    }

    @Test
    @Order(4)
    void testIfProgramCanCalculateMoneyLossOfAllExpiredProducts(){
        assertEquals(600, storage.calculateMoneylossOfExpiredProducts());
    }

    @Test
    @Order(5)
    void testIfProgramCanGiveCategoryNamesOrderedByMoneylossDesc(){
        assertThat(storage.listCategoriesOrderedByMoneylossDesc())
                .containsExactly("ital", "főzés", "üdítő", "cukros", "tejtermék");
    }

    @Test
    @Order(6)
    void testIfProgramCorrectlyRemovesExpiredProducts(){
        assertThat(storage.getProducts())
                .hasSize(10)
                .contains(product1, product2, product3, product4, product5, product6, product7, product8, product9, product10);

        storage.removeAllExpiredProducts();

        assertThat(storage.getProducts())
                .hasSize(7)
                .containsExactlyInAnyOrder(product1, product2, product4, product5, product6, product7, product10);
    }
}