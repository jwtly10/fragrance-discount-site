import {
    Card,
    CardBody,
    CardImg,
    CardSubtitle,
    CardTitle,
} from 'react-bootstrap'

function Product({ product }: { product: Product }) {
    console.log(product)
    return (
        <Card>
            <div className="product-image d-flex justify-content-center">
                <img
                    src="https://s3.eu-central-1.amazonaws.com/bootstrapbaymisc/blog/24_days_bootstrap/vans.png"
                    style={{ width: '300px' }}
                    alt="Vans"
                />
                <span className="product-discount-label bg-danger">
                    -{product.discount}% OFF
                </span>
            </div>
            <CardBody>
                <CardTitle>{product.itemName}</CardTitle>
                <CardSubtitle>{product.type}</CardSubtitle>
                <div className="buy d-flex justify-content-between align-items-center">
                    <div className="price text-dark">
                        <h5 className="mt-4">Now £{product.currentPrice}</h5>
                    </div>
                    <div className="price text-danger">
                        <h5 className="mt-4">Was £{product.oldPrice}</h5>
                    </div>
                    <div className="price text-success">
                        <h5 className="mt-4">Save £{product.saving}</h5>
                    </div>
                    <a href="#" className="btn btn-danger mt-3">
                        <i className="fas fa-shopping-cart"></i> Add to Cart
                    </a>
                </div>
            </CardBody>
        </Card>
    )
}

export default Product
