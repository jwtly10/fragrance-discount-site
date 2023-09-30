import { Container, Form } from 'react-bootstrap'

function Filter({
    handleFilter,
    currentFilter,
}: {
    handleFilter: (filter: string) => void
    currentFilter: string
}) {
    return (
        <Container>
            <Form.Label>Sort By</Form.Label>
            <Form.Select
                onChange={(e) => handleFilter(e.target.value)}
                className="form-select mw-25"
                defaultValue={currentFilter}
            >
                <option value="max_discount">Discount % High To Low</option>
                <option defaultChecked value="max_saving">
                    Saving £ High To Low
                </option>
                <option defaultChecked value="max_price">
                    Price High To Low
                </option>
            </Form.Select>
        </Container>
    )
}

export default Filter
