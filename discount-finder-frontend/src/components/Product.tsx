import { Card, CardBody, CardSubtitle, CardTitle } from 'react-bootstrap'

function Product({ product }: { product: Product }) {
    return (
        <div className="psuedo-border">
            <Card className="p-1" style={{ maxWidth: 300 }}>
                <div className="product-image d-flex justify-content-center">
                    <img
                        src={product.imageUrl}
                        style={{ width: '300px' }}
                        alt="Vans"
                    />
                    <span className="product-discount-label bg-danger">
                        -{product.discount}% OFF
                    </span>
                </div>
                <CardBody>
                    <CardSubtitle>{product.brand}</CardSubtitle>
                    <CardTitle>{product.itemName}</CardTitle>
                    <CardSubtitle>{product.type}</CardSubtitle>
                    <div className="buy d-flex justify-content-between align-items-center">
                        <div className="price text-dark">
                            <h5 className="mt-4">
                                Now £{product.currentPrice}
                            </h5>
                        </div>
                        <div className="price text-danger">
                            <h5 className="mt-4">Was £{product.oldPrice}</h5>
                        </div>
                        <div className="price text-success">
                            <h5 className="mt-4">Save £{product.saving}</h5>
                        </div>
                        <a
                            href={product.url}
                            target="_blank"
                            className="btn btn-danger mt-3">
                            <i className="fas fa-shopping-cart"></i> Buy Now
                        </a>
                    </div>
                </CardBody>
            </Card>
        </div>
    )
}

export default Product
