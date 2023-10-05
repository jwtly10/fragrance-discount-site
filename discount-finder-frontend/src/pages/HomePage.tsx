import { Container } from 'react-bootstrap'

function HomePage() {
    return (
        <Container className="d-flex mt-5 flex-column justify-content-center align-items-center">
            <h1>Fragrance Finder UK</h1>
            <p>Discover discounted perfumes from a range of stores.</p>
            <div className="d-flex flex-column align-items-center">
                <h4>
                    <a href="/products/mens" className="text-decoration-none">
                        Browse Mens Range
                    </a>
                </h4>
                <h4>
                    <a href="/products/womens" className="text-decoration-none">
                        Browse Womens Range
                    </a>
                </h4>
            </div>
        </Container>
    )
}

export default HomePage
