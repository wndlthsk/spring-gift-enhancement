package gift.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public class CategoryDto {
    private Long id;
    @NotNull(message = "카테고리 name 입력은 필수 입니다.")
    private String name;
    @NotNull(message = "카테고리 color 입력은 필수 입니다.")
    private String color;
    @NotNull(message = "카테고리 imageUrl 입력은 필수 입니다.")
    private String imageUrl;
    private String description;

    public CategoryDto() {
    }

    public CategoryDto(Long id, String name, String color, String imageUrl,String description) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.imageUrl = imageUrl;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}