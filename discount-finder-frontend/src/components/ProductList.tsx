import { Col, Container, Row } from 'react-bootstrap'
import Product from './Product'
import PlaceholderTemp from './PlaceholderTemp'

function ProductList({
    products,
    isLoading,
}: {
    products: Product[]
    isLoading: boolean
}) {
    return (
        <>
            {products.length === 0 && !isLoading && (
                <p className="text-dark text-center">No products found</p>
            )}
            <Container className="d-flex justify-content-center align-items-center">
                <Row>
                    {isLoading ? (
                        <PlaceholderTemp />
                    ) : (
                        products.map((product: Product, index: number) => (
                            <Col md={4} key={index} className="mb-4">
                                <Product product={product} />
                            </Col>
                        ))
                    )}
                </Row>
            </Container>
        </>
    )
}

export default ProductList
