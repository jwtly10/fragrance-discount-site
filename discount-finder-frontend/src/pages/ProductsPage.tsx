import ProductList from '../components/ProductList'
import { useEffect, useState } from 'react'
import apiService from '../services/apiService'
import { useParams, useSearchParams } from 'react-router-dom'
import { Container } from 'react-bootstrap'
import FilterOptions from '../components/FilterOptions'

function ProductsPage() {
    const [error, setError] = useState<string>('')
    const [products, setProducts] = useState<Product[]>([])
    const [isLoading, setIsLoading] = useState<boolean>(true)
    const [filteredProducts, setFilteredProducts] = useState<Product[]>([])

    const { gender } = useParams()
    const [sortParam, setSortParam] = useSearchParams()

    useEffect(() => {
        getProducts(gender, sortParam.get('sort') || 'none')
    }, [])

    function handleSearch(search: string) {
        setFilteredProducts(
            products.filter((product) => {
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
        )
    }

    function handleFilter(filter: string) {
        setSortParam((params) => {
            params.set('sort', filter)
            return params
        })

        // add server side sorting
        setFilteredProducts(
            filteredProducts.sort((a, b) => {
                switch (filter) {
                    case 'max_discount':
                        return b.discount - a.discount
                    case 'max_price':
                        return b.currentPrice - a.currentPrice
                    case 'max_saving':
                        return b.saving - a.saving
                    case 'none':
                        return 0
                    default:
                        return 0
                }
            })
        )
    }

    function getProducts(gender: string = 'null', filter: string = 'none') {
        setError('')
        setProducts([])
        setIsLoading(true)

        apiService
            .getProducts(gender, filter)
            .then((products) => {
                setProducts(products)
                setFilteredProducts(products)
                setIsLoading(false)
            })
            .catch((error) => {
                setError(error)
                setIsLoading(false)
            })
    }

    return (
        <Container>
            {error ? (
                <p className="text-danger text-center">{error}</p>
            ) : (
                <>
                    <h1 className="text-center mb-4">
                        {gender == 'mens' ? 'Mens Products' : 'Womens Products'}
                    </h1>
                    <FilterOptions
                        handleSearch={handleSearch}
                        handleFilter={handleFilter}
                        currentFilter={sortParam.get('sort') || 'none'}
                    />

                    <ProductList
                        products={filteredProducts}
                        isLoading={isLoading}
                    />
                </>
            )}
        </Container>
    )
}

export default ProductsPage
