import { Form } from 'react-bootstrap'
function Search({
    setSearch,
    search,
}: {
    setSearch: (e: string) => void
    search: string
}) {
    return (
        <Form.Control
            type="text"
            className="mb-3"
            onChange={(e) => setSearch(e.target.value)}
        />
    )
}

export default Search
