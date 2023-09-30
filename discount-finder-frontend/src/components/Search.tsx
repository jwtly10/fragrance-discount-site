import { Form } from 'react-bootstrap'
function Search({ setSearch }: { setSearch: (e: string) => void }) {
    return (
        <Form.Control
            type="text"
            onChange={(e) => setSearch(e.target.value)}
            placeholder="Search Brand or Product"
        />
    )
}

export default Search
