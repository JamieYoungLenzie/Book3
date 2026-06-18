import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { useLocalState } from './useLocalStorage';

const SubsectorListComponent = () => {

    const [items, setItems] = useState(null);
    const [error, setError] = useState(null);
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [loading, setLoading] = useState(true);

    const url = "/list";

    useEffect(() => {

        setLoading(true); // Indicate data loading
        setError(null); // Clear previous errors

        fetch(url, {
            method: 'GET',
            headers: {
                accept: 'application/json',
                Authorization: jwt
            }
        }).then(res => {
            if (!res.ok) {
                if (res.status === 500) {
                    return res.text().then(text => {
                        console.error(text);
                        const errorData = JSON.parse(text);
                        setError(errorData.message);
                    });
                } else {
                    setError("Unknown error.");
                }
                return;
            }
            return res.json();
        }).then(data => {
            console.log(data);
            setItems(data);
        }).catch(err => {
            console.error(err.message);
            setError(err.message);
        }).finally(() => {
            setLoading(false);
        });
    }, [url, jwt]);

    return (
        <ul>
            {loading && <p>Loading data...</p>}
            {items && items.map(item => (
                <li key={item.name}>
                    <Link to={`/load/${item.name}`}>
                        {item.name}
                    </Link>
                </li>
            ))
            }
            {error && <p className="error">{error}</p>}
        </ul>
    );
}

export default SubsectorListComponent;