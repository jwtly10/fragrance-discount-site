import { Form } from 'react-bootstrap'

function Filter({
    handleFilter,
    currentFilter,
}: {
    handleFilter: (filter: string) => void
    currentFilter: string
}) {
    return (
        <div className="container">
            <div className="row">
                <div className="col-12">
                    <Form.Select
                        onChange={(e) => handleFilter(e.target.value)}
                        className="form-select"
                        defaultValue={currentFilter}>
                        <option value="max_discount">
                            Discount High To Low
                        </option>
                        <option defaultChecked value="max_price">
                            Price High To Low
                        </option>
                    </Form.Select>
                </div>
            </div>
        </div>
    )
}

export default Filter
