import { Container } from 'react-bootstrap'

import 'bootstrap/dist/css/bootstrap.min.css'

import NavBar from './components/NavBar'
import AppRouter from './components/AppRouter'

function App() {
    return (
        <>
            <NavBar />
            <Container>
                <AppRouter />
            </Container>
        </>
    )
}

export default App
