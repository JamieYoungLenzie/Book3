import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useLocalState } from './useLocalStorage';

const SystemCreateComponent = () => {

    const [items, setItems] = useState(null);
    const [error, setError] = useState(null);
    const [jwt, setJwt] = useLocalState("", "jwt");
    const [subsectorName, setSubsectorName] = useState('');
    const [loading, setLoading] = useState(true);
    const navigate = useNavigate();
    
    const url = "/create";

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
            res.json().then(data => {
                setItems(data);
            }).catch(err => {
                console.error(err);
                setError(err.message);
            }).finally(() => {
                setLoading(false);
            });
        }).catch(err => {
            console.log(err);
            setError(err.message)
        });
    }, [url, jwt]);

    const handleSubmit = (e) => {

        const hostSubmit = window.location.host;
        const protocolSubmit = window.location.protocol;
        const urlSubmit = protocolSubmit + "//" + hostSubmit + "/save";

        const subsector = {
            "name": "",
            "worlds": []
        };

        subsector.name = subsectorName;
        subsector.worlds = items;

        fetch(urlSubmit, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                Authorization: jwt
            },
            body: JSON.stringify(subsector)
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
            console.log('saved OK');
            navigate('/');
        }).catch(err => {
            console.error(err);
            setError(err.message);
        });
    }

    return (
        <div>
            <ul>
                {loading && <p>Loading data...</p>}
                {items && items.map(item => (
                    <li key={item.name}><span className="upp">{item.location} {item.name} {item.upp}</span></li>
                ))}
                {error && <p className="error">{error}</p>}
            </ul>
            <input type="text"
                id="txtSubsector"
                placeholder="Subsector name"
                value={subsectorName}
                onChange={(e) => setSubsectorName(e.target.value)}></input>
            <button type="button"
                disabled={!subsectorName}
                default
                onClick={(e) => handleSubmit()}>Save</button>
        </div>
    );
};

export default SystemCreateComponent;