import { Button, Col, Container, Row } from 'react-bootstrap'
import Search from './Search'
import Filter from './Filter'
import { Popover } from 'antd'

function FilterOptions({
    setSearch,
    handleFilter,
    currentFilter,
}: {
    setSearch: (e: string) => void
    handleFilter: (e: string) => void
    currentFilter: string
}) {
    const content = (
        <div>
            <Filter handleFilter={handleFilter} currentFilter={currentFilter} />
        </div>
    )

    return (
        <Container className="w-100 mb-4 d-flex flex-column flex-md-row justify-content-center align-items-center m-0 p-0">
            <Row className="w-100">
                <Col xs={8}>
                    <Search setSearch={setSearch} />
                </Col>

                <Col className="">
                    <Popover trigger="click" content={content}>
                        <Button className="btn-primary w-100">Filters</Button>
                    </Popover>
                </Col>
            </Row>
        </Container>
    )
}

export default FilterOptions
