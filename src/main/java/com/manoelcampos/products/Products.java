package com.manoelcampos.products;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma lista de produtos.
 * Tal classe é necessária para permitir que
 * seja feito o parse de um arquivo XML contendo
 * uma lista de produtos (objetos da classe {@link Product}).
 *
 * <p>Não temos como usar a biblioteca JAXB para
 * converter diretamente tal lista de produtos no XML
 * para um objeto {@code List<Product>}.</p>
 *
 * @author Manoel Campos da Silva Filho
 */
@XmlAccessorType(XmlAccessType.FIELD) //indica que os campos da classe vão ser convertidos de/para XML
public class Products
{
    /**
     * Indica que cada objeto da lista de produtos
     * é definido como uma tag product dentro do XML.
     */
    @XmlElement(name = "product")
    private List<Product> products;

    public Products(){
        products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(final List<Product> products) {
        this.products = products;
    }
}