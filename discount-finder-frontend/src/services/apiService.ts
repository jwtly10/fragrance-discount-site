import axios from 'axios'

const API_URL = import.meta.env.VITE_API_URL

async function getProducts(
    gender: string,
    sortParam: string = 'mens'
): Promise<Product[]> {
    let products: Product[] = []
    return axios
        .get(API_URL + 'api/v1/products/' + gender + '?sort=' + sortParam)
        .then((response) => {
            products = response.data
            return products
        })
        .catch((error) => {
            throw error.response
        })
}

export default { getProducts }
