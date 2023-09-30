import Container from 'react-bootstrap/Container'
import Nav from 'react-bootstrap/Nav'
import Navbar from 'react-bootstrap/Navbar'

function NavBar() {
    return (
        <Navbar expand="lg">
            <Container>
                <Navbar.Brand href="/">discount-finder-frontend</Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav className="">
                        <Nav.Link href="/products/mens">Mens</Nav.Link>
                        <Nav.Link href="/products/womens">Womens</Nav.Link>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
    )
}

export default NavBar
