import { Form } from 'react-bootstrap'
function Search({ handleSearch }: { handleSearch: (e: string) => void }) {
    return (
        <Form.Control
            type="text"
            onChange={(e) => handleSearch(e.target.value)}
            placeholder="Search Brand or Product"
        />
    )
}

export default Search
