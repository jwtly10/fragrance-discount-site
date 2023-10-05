import { ChangeEvent, useState } from 'react'
import { Button, Container, Form } from 'react-bootstrap'

function Filter({
    handleFilter,
    currentFilter,
}: {
    handleFilter: (filter: string) => void
    currentFilter: string
}) {
    const [optionState, setOptionState] = useState(currentFilter)

    function handleOptionChange(e: ChangeEvent<HTMLSelectElement>) {
        setOptionState(e.target.value)
        handleFilter(e.target.value)
    }

    function handleClear() {
        setOptionState('none')
        handleFilter('none')
    }

    return (
        <Container>
            <Form.Label>Sort By</Form.Label>
            <Form.Select
                onChange={(e) => {
                    handleOptionChange(e)
                }}
                className="form-select mw-25 mb-3"
                defaultValue={currentFilter}
                value={optionState}>
                <option value="none">None</option>
                <option defaultChecked value="max_price">
                    Price High To Low
                </option>
                <option value="max_discount">Discount High To Low</option>
                <option defaultChecked value="max_saving">
                    Savings High To Low
                </option>
            </Form.Select>

            {/* <Form.Label>Filter By Brand</Form.Label>
            <Form.Select className="mb-3">
                <option value="none">None</option>
                <option value="calvin_klein">Calvin Klein</option>
                <option value="amarni">Amarni</option>
            </Form.Select> */}
            <Button
                onClick={() => {
                    handleClear()
                }}
                className="w-100">
                Clear Filters
            </Button>
        </Container>
    )
}

export default Filter
