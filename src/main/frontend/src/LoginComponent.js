import { useLocalState } from "./useLocalStorage";
import TravellerComponent from "./TravellerComponent";
import { useState, useEffect } from "react";

const LoginComponent = () => {
    const [jwt, setJwt] = useLocalState(null, "jwt");
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const [loading, setLoading] = useState(false);
    const [auth, setAuth] = useState(false);

    // Auto-login if we already have a valid JWT
    //useEffect(() => {
    //    if (jwt) {
    //        setAuth(true);
    //    }
    //}, [jwt]);

    const handleSubmit = async (e) => {
        e?.preventDefault?.();
        setError(null);
        setLoading(true);

        const url = "/authenticate";

        try {
            const response = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password }),
            });

            if (!response.ok) {
                const text = await response.text();
                let message = "Login failed";

                if (response.status === 401) {
                    message = "Invalid credentials";
                } else if (response.status === 500) {
                    try {
                        const errorData = JSON.parse(text);
                        message = errorData.message || "Server error";
                    } catch {
                        message = "Server error";
                    }
                }
                setError(message);
                return;
            }

            const data = await response.json();

            // Extract token from header or body (most common patterns)
            let token = response.headers.get('Authorization') ||
                       data.token ||
                       data.jwt ||
                       data.accessToken;

            if (!token) {
                setError("No token received from server");
                return;
            }

            // Save token and mark as authenticated
            setJwt(token);
            setAuth(true);

            console.log("JWT saved successfully");

        } catch (err) {
            console.error(err);
            setError("Network error. Please check your connection.");
        } finally {
            setLoading(false);
        }
    };

    // If authenticated, show main app
    if (auth) {
        return <TravellerComponent />;
    }

    return (
        <div className="App">
            <div id="content">
                <header>Traveller</header>
                <menu></menu>
                <nav></nav>
                <footer>2024</footer>

                <section>
                    <h2>Login</h2>

                    <div className="input-wrapper">
                        <input
                            type="text"
                            id="username"
                            placeholder="User name"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            disabled={loading}
                        />
                    </div>

                    <div className="input-wrapper">
                        <input
                            type="password"
                            id="password"
                            placeholder="Password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            disabled={loading}
                        />
                    </div>

                    {error && <p className="error">{error}</p>}

                    <button
                        type="button"
                        id="submit"
                        onClick={handleSubmit}
                        disabled={loading || !username.trim() || !password.trim()}
                    >
                        {loading ? "Logging in..." : "Log in"}
                    </button>
                </section>
            </div>
        </div>
    );
};

export default LoginComponent;