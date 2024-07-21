package gift;

import gift.domain.Category;
import gift.domain.Option;
import gift.domain.Product;
import gift.dto.OptionDto;
import gift.repository.ProductRepository;
import gift.service.ProductService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductServiceOptionTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        Category category = new Category("category", "color", "image", "");
        product = new Product(5L, "Test Product", 100, "image.jpg", category);
    }

    @Test
    public void testGetOptionsByProductId() {
        Option option = new Option("Test Option", 10, product);
        product.addOption(option);
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

        List<OptionDto> optionDtoList = productService.getOptionsByProductId(product.getId());

        assertEquals(1, optionDtoList.size());
        assertEquals("Test Option", optionDtoList.get(0).getName());
        assertEquals(10, optionDtoList.get(0).getQuantity());
    }

    @Test
    public void testSaveOption_ProductNotFound() {
        OptionDto optionDto = new OptionDto(1L, "Test Option", 100);
        given(productRepository.findById(product.getId())).willReturn(Optional.empty());

        assertThatExceptionOfType(NoSuchElementException.class)
            .isThrownBy(()->productService.saveOption(product.getId(), optionDto));
    }

    @Test
    public void testSaveOption_DuplicateOptionName() {
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

        Option existingOption = new Option("Test Option", 5, product);
        product.addOption(existingOption);
        given(productRepository.findById(product.getId())).willReturn(Optional.of(product)); // option이 들어간 product 리턴

        OptionDto optionDto = new OptionDto("Test Option", 10);

        assertThatExceptionOfType(IllegalStateException.class)
            .isThrownBy(()->productService.saveOption(product.getId(), optionDto));
    }
}
