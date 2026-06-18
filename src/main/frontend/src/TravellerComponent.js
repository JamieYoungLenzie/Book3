import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import SubsectorListComponent from "./SubsectorListComponent";
import HomeComponent from "./HomeComponent";
import SystemListComponent from "./SystemListComponent";
import SystemEditComponent from "./SystemEditComponent";
import NoPageComponent from "./NoPageComponent";
import SubsectorCreateComponent from "./SubsectorCreateComponent";

const TravellerComponent = () => {

    return (
        <BrowserRouter>
            <div className="App">
                <div id="content">
                    <header><p>Traveller</p></header>
                    <menu>
                        <ul>
                            <li><Link to="/">Home</Link></li>
                            <li><Link to="/create">Create</Link></li>
                            <li><Link to="/admin">Admin</Link></li>
                        </ul>
                    </menu>
                    <nav>
                        <SubsectorListComponent />
                    </nav>
                    <footer><p>2024</p></footer>
                    <main>
                        <Routes>
                            <Route exact path="/" element={<HomeComponent />} />
                            <Route path="/load/:name" element={<SystemListComponent />} />
                            <Route path="/create" element={<SubsectorCreateComponent />} />
                            <Route path="/edit/:location" element={<SystemEditComponent />} />
                            <Route path="*" element={<NoPageComponent />} />
                        </Routes>
                    </main>
                </div>
            </div>
        </BrowserRouter>
    );
}

export default TravellerComponent;