package models;

import com.avaje.ebean.Model;

import javax.persistence.*;

@Entity
@Table(name = "itementity")
public class ItemEntity extends Model{

    public static Finder<Long, ItemEntity> FINDER = new Finder<>(ItemEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Item")
    private Long id;
    private int quantity;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="product_id")
    private ProductEntity product;

    //TODO agregar wishlist como objeto
    //@ManyToOne(fetch=FetchType.LAZY, optional=false)
    //@JoinColumn(name="wihslist_id")
    private Long wishlist;

    public ItemEntity() {
        this.id=null;
        this.quantity = -1;
        this.product = null;
    }

    public ItemEntity(int quantity, ProductEntity product, Long wishlist) {
        this.quantity = quantity;
        this.product = product;
        this.wishlist = wishlist;
    }

    public ItemEntity(Long id) {
        this();
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public Long getWishlist() {
        return wishlist;
    }

    public void setWishlist(Long wishlist) {
        this.wishlist = wishlist;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "id=" + id +
        ", product="+ product.toString()+
                '}';
    }
}