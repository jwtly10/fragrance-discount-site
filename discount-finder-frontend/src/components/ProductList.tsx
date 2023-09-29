import { Col, Container, Row } from 'react-bootstrap'
import Product from './Product'

function ProductList({ products }: { products: Product[] }) {
    return (
        <Container>
            <Row>
                {products.map((product: Product, index: number) => (
                    <Col key={index} className="mb-4 mb-md-0">
                        <Product product={product} />
                    </Col>
                ))}
            </Row>
        </Container>
    )
}

export default ProductList
