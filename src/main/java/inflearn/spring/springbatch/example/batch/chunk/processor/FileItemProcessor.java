package inflearn.spring.springbatch.example.batch.chunk.processor;

import inflearn.spring.springbatch.example.batch.domain.Product;
import inflearn.spring.springbatch.example.batch.domain.ProductVO;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;

public class FileItemProcessor implements ItemProcessor<ProductVO, Product> {

    @Override
    public Product process(ProductVO item) throws Exception {
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(item, Product.class);
        return product;
    }
}
