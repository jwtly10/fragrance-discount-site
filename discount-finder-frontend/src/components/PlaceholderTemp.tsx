import { Col } from 'react-bootstrap'
import PlaceholderProduct from './PlaceholderProductList'

function PlaceholderTemp() {
    return (
        <>
            <p className="text-warning text-center">
                {' '}
                The first load might take up to 10 seconds... We have thousands
                of products to process to find you the best deal 🤭
            </p>
            {[...Array(12)].map((_, index: number) => {
                return (
                    <Col key={index} className="mb-4">
                        <PlaceholderProduct />
                    </Col>
                )
            })}
        </>
    )
}

export default PlaceholderTemp
