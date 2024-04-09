package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    @Mapping(source = "name", target = "title")
    @Mapping(source = "cost", target = "price")
    @Mapping(source = "barcode", target = "vendorCode")
    ProductDTO toProductDTO(Product product);

    @Mapping(source = "title", target = "name")
    @Mapping(source = "price", target = "cost")
    @Mapping(source = "vendorCode", target = "barcode")
    Product toProduct(ProductCreateDTO productData);

    @Mapping(source = "price", target = "cost")
    Product toProduct(ProductUpdateDTO productData);
}
