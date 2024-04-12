package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

@Component
public class ProductSpecification {

    public Specification<Product> build(ProductParamsDTO params) {
        return withCategoryId(params.getCategoryId())
                .and(priceBetween(params.getPriceLt(), params.getPriceGt()))
                .and(withRatingGt(params.getRatingGt()))
                .and(withTitleCont(params.getTitleCont()));
    }
    private Specification<Product> withCategoryId(Long categoryId) {
        return (root, query, cb) -> categoryId == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), categoryId);
    }

    private Specification<Product> priceBetween(Integer priceLt, Integer priceGt) {
        return (root, query, cb) -> priceGt == null || priceLt == null ? cb.conjunction() : cb.between(root.get("price"), priceLt, priceGt);
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, cb) -> ratingGt == null ? cb.conjunction() : cb.greaterThan(root.get("rating"), ratingGt);
    }

    private Specification<Product> withTitleCont(String titleCont) {
        return (root, query, cb) -> titleCont == null ? cb.conjunction() : cb.like(cb.lower(root.get("title")), "%" + titleCont + "%");
    }
}
