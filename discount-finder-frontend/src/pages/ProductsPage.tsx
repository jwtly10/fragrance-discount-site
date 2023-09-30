import ProductList from '../components/ProductList'
import Search from '../components/Search'
import { useEffect, useState } from 'react'
import apiService from '../services/apiService'
import { useParams, useSearchParams } from 'react-router-dom'
import Filter from '../components/Filter'

function ProductsPage() {
    const [error, setError] = useState<string>('')
    const [search, setSearch] = useState<string>('')
    const [products, setProducts] = useState<Product[]>([])
    const [isLoading, setIsLoading] = useState<boolean>(false)

    const { gender } = useParams()
    const [sortParam, setSortParam] = useSearchParams()

    useEffect(() => {
        getProducts(gender, sortParam.get('sort') || 'max_discount')
    }, [])

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

    function handleFilter(filter: string) {
        setSortParam((params) => {
            params.set('sort', filter)
            return params
        })
        getProducts(gender, sortParam.get('sort') || 'max_discount')
    }

    function getProducts(
        gender: string = 'null',
        filter: string = 'max_discount'
    ) {
        setError('')
        setProducts([])
        setIsLoading(true)
        apiService
            .getProducts(gender, filter)
            .then((products) => {
                setProducts(products)
                setIsLoading(false)
            })
            .catch((error) => {
                setError(error.data.message)
                setIsLoading(false)
            })
    }

    return (
        <>
            {error ? (
                <p className="text-danger text-center">{error}</p>
            ) : (
                <>
                    <Search setSearch={setSearch} search={search} />
                    <Filter
                        handleFilter={handleFilter}
                        currentFilter={sortParam.get('sort') || 'max_discount'}
                    />
                    <ProductList
                        products={filteredProducts}
                        isLoading={isLoading}
                    />
                </>
            )}
        </>
    )
}

export default ProductsPage
