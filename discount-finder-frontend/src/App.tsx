import { Container } from 'react-bootstrap'
import ProductList from './components/ProductList'
import 'bootstrap/dist/css/bootstrap.min.css'

function App() {
    const products: Product[] = [
        {
            url: 'https://www.amazon.com/Apple-iPhone-12-128GB-Blue/dp/B08L5TNJHG/ref=sr_1_1?dchild=1&keywords=iphone+12&qid=1627667893&sr=8-1',
            brand: 'Apple',
            itemName: 'Apple iPhone 12',
            currentPrice: 799.99,
            type: 'phone',
            oldPrice: 829.99,
            saving: 30,
            discount: 3.61,
            imageUrl:
                'https://m.media-amazon.com/images/I/71ZOtNdaZCL._FMwebp__.jpg',
            inStock: true,
            size: '10ml',
            site: {
                name: 'Amazon',
                url: 'https://www.amazon.com/',
            },
        },
        {
            url: 'https://www.amazon.com/Apple-iPhone-12-128GB-Blue/dp/B08L5TNJHG/ref=sr_1_1?dchild=1&keywords=iphone+12&qid=1627667893&sr=8-1',
            brand: 'Apple',
            itemName: 'Apple iPhone 12',
            currentPrice: 799.99,
            type: 'phone',
            oldPrice: 829.99,
            saving: 30,
            discount: 3.61,
            imageUrl:
                'https://m.media-amazon.com/images/I/71ZOtNdaZCL._FMwebp__.jpg',
            inStock: true,
            size: '10ml',
            site: {
                name: 'Amazon',
                url: 'https://www.amazon.com/',
            },
        },
        {
            url: 'https://www.amazon.com/Apple-iPhone-12-128GB-Blue/dp/B08L5TNJHG/ref=sr_1_1?dchild=1&keywords=iphone+12&qid=1627667893&sr=8-1',
            brand: 'Apple',
            itemName: 'Apple iPhone 12',
            currentPrice: 799.99,
            type: 'phone',
            oldPrice: 829.99,
            saving: 30,
            discount: 3.61,
            imageUrl:
                'https://m.media-amazon.com/images/I/71ZOtNdaZCL._FMwebp__.jpg',
            inStock: true,
            size: '10ml',
            site: {
                name: 'Amazon',
                url: 'https://www.amazon.com/',
            },
        },
        {
            url: 'https://www.amazon.com/Apple-iPhone-12-128GB-Blue/dp/B08L5TNJHG/ref=sr_1_1?dchild=1&keywords=iphone+12&qid=1627667893&sr=8-1',
            brand: 'Apple',
            itemName: 'Apple iPhone 12',
            currentPrice: 799.99,
            type: 'phone',
            oldPrice: 829.99,
            saving: 30,
            discount: 3.61,
            imageUrl:
                'https://m.media-amazon.com/images/I/71ZOtNdaZCL._FMwebp__.jpg',
            inStock: true,
            size: '10ml',
            site: {
                name: 'Amazon',
                url: 'https://www.amazon.com/',
            },
        },
        {
            url: 'https://www.amazon.com/Apple-iPhone-12-128GB-Blue/dp/B08L5TNJHG/ref=sr_1_1?dchild=1&keywords=iphone+12&qid=1627667893&sr=8-1',
            brand: 'Apple',
            itemName: 'Apple iPhone 12',
            currentPrice: 799.99,
            type: 'phone',
            oldPrice: 829.99,
            saving: 30,
            discount: 3.61,
            imageUrl:
                'https://m.media-amazon.com/images/I/71ZOtNdaZCL._FMwebp__.jpg',
            inStock: true,
            size: '10ml',
            site: {
                name: 'Amazon',
                url: 'https://www.amazon.com/',
            },
        },
        {
            url: 'https://www.amazon.com/Apple-iPhone-12-128GB-Blue/dp/B08L5TNJHG/ref=sr_1_1?dchild=1&keywords=iphone+12&qid=1627667893&sr=8-1',
            brand: 'Apple',
            itemName: 'Apple iPhone 12',
            currentPrice: 799.99,
            type: 'phone',
            oldPrice: 829.99,
            saving: 30,
            discount: 3.61,
            imageUrl:
                'https://m.media-amazon.com/images/I/71ZOtNdaZCL._FMwebp__.jpg',
            inStock: true,
            size: '10ml',
            site: {
                name: 'Amazon',
                url: 'https://www.amazon.com/',
            },
        },
        {
            url: 'https://www.amazon.com/Apple-iPhone-12-128GB-Blue/dp/B08L5TNJHG/ref=sr_1_1?dchild=1&keywords=iphone+12&qid=1627667893&sr=8-1',
            brand: 'Apple',
            itemName: 'Apple iPhone 12',
            currentPrice: 799.99,
            type: 'phone',
            oldPrice: 829.99,
            saving: 30,
            discount: 3.61,
            imageUrl:
                'https://m.media-amazon.com/images/I/71ZOtNdaZCL._FMwebp__.jpg',
            inStock: true,
            size: '10ml',
            site: {
                name: 'Amazon',
                url: 'https://www.amazon.com/',
            },
        },
        {
            url: 'https://www.amazon.com/Apple-iPhone-12-128GB-Blue/dp/B08L5TNJHG/ref=sr_1_1?dchild=1&keywords=iphone+12&qid=1627667893&sr=8-1',
            brand: 'Apple',
            itemName: 'Apple iPhone 12',
            currentPrice: 799.99,
            type: 'phone',
            oldPrice: 829.99,
            saving: 30,
            discount: 3.61,
            imageUrl:
                'https://m.media-amazon.com/images/I/71ZOtNdaZCL._FMwebp__.jpg',
            inStock: true,
            size: '10ml',
            site: {
                name: 'Amazon',
                url: 'https://www.amazon.com/',
            },
        },
        {
            url: 'https://www.amazon.com/Apple-iPhone-12-128GB-Blue/dp/B08L5TNJHG/ref=sr_1_1?dchild=1&keywords=iphone+12&qid=1627667893&sr=8-1',
            brand: 'Apple',
            itemName: 'Apple iPhone 12',
            currentPrice: 799.99,
            type: 'phone',
            oldPrice: 829.99,
            saving: 30,
            discount: 3.61,
            imageUrl:
                'https://m.media-amazon.com/images/I/71ZOtNdaZCL._FMwebp__.jpg',
            inStock: true,
            size: '10ml',
            site: {
                name: 'Amazon',
                url: 'https://www.amazon.com/',
            },
        },
    ]

    return (
        <>
            <Container>
                <h1 className="text-center">Discount-Finder-Frontend</h1>
                <ProductList products={products} />
            </Container>
        </>
    )
}

export default App
