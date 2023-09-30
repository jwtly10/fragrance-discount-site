import { Skeleton } from 'antd'
import { Card, CardBody } from 'react-bootstrap'

function PlaceholderProduct() {
    return (
        <div className="psuedo-border">
            <Card className="p-1 w-90">
                <div className="product-image d-flex justify-content-center align-items-center">
                    <Skeleton.Image
                        active
                        style={{ width: 300, height: 300 }}
                    />
                </div>
                <CardBody>
                    <Skeleton active />
                </CardBody>
            </Card>
        </div>
    )
}

export default PlaceholderProduct
