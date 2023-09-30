import { Container } from 'react-bootstrap'
import ProductList from './components/ProductList'
import 'bootstrap/dist/css/bootstrap.min.css'
import { useEffect, useState } from 'react'
import apiService from './services/apiService'

function App() {
    const [products, setProducts] = useState<Product[]>([])
    const [error, setError] = useState<string>('')

    useEffect(() => {
        setError('')
        setProducts([])
        apiService
            .getProducts('mens', '')
            .then((products) => {
                setProducts(products)
            })
            .catch((error) => {
                setError(error.data.message)
            })
    }, [])

    return (
        <>
            <Container>
                <h1 className="text-center">Discount-Finder-Frontend</h1>
                {<p className="text-danger">{error}</p>}
                <ProductList products={products} />
            </Container>
        </>
    )
}

export default App
