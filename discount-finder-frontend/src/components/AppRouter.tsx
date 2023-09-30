import { BrowserRouter, Route, Routes } from 'react-router-dom'
import ProductsPage from '../pages/ProductsPage'
import HomePage from '../pages/HomePage'

function AppRouter() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<HomePage />} />
                <Route path="products/:gender" element={<ProductsPage />} />
                <Route path="*" element={<h1>Not Found</h1>} />
            </Routes>
        </BrowserRouter>
    )
}

export default AppRouter
