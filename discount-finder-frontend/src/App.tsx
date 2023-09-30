import { Container } from 'react-bootstrap'
import ProductList from './components/ProductList'
import 'bootstrap/dist/css/bootstrap.min.css'
import { useEffect, useState } from 'react'
import apiService from './services/apiService'
import Search from './components/Search'

function App() {
    const [products, setProducts] = useState<Product[]>([])
    const [error, setError] = useState<string>('')
    const [search, setSearch] = useState<string>('')

    const filteredProducts = products.filter((product) => {
        return (
            product.itemName
                .toLowerCase()
                .replace(/\s/g, '')
                .includes(search.toLowerCase().replace(/\s/g, '')) ||
            product.brand
                .toLowerCase()
                .replace(/\s/g, '')
                .includes(search.toLowerCase().replace(/\s/g, ''))
        )
    })

    useEffect(() => {
        setError('')
        setProducts([])
        apiService
            .getProducts('mens', 'max_discount')
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
                {error && <p className="text-danger">{error}</p>}
                <Search setSearch={setSearch} search={search} />
                <ProductList products={filteredProducts} />
            </Container>
        </>
    )
}

export default App
