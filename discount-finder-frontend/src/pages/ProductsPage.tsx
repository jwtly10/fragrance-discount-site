import ProductList from '../components/ProductList'
import Search from '../components/Search'
import { useEffect, useState } from 'react'
import apiService from '../services/apiService'
import { useParams } from 'react-router-dom'

function ProductsPage() {
    const [error, setError] = useState<string>('')
    const [search, setSearch] = useState<string>('')
    const [products, setProducts] = useState<Product[]>([])

    const { gender } = useParams()

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
            .getProducts(gender || 'null', 'max_discount')
            .then((products) => {
                setProducts(products)
            })
            .catch((error) => {
                setError(error.data.message)
            })
    }, [])
    return (
        <>
            {error ? (
                <p className="text-danger text-center">{error}</p>
            ) : (
                <>
                    <Search setSearch={setSearch} search={search} />
                    <ProductList products={filteredProducts} />
                </>
            )}
        </>
    )
}

export default ProductsPage
