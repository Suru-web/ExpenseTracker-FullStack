import { useState } from "react";
import api from "../api/api";
import { useAuth } from "../auth/AuthContext";

export default function Login() {
    const { login } = useAuth();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const submit = async (e) => {
        e.preventDefault();
        const res = await api.post("/auth/login", { email, password });
        login(res.data.token);
    };

    return (
        <div style={styles.page}>
            <form style={styles.card} onSubmit={submit}>
                <h2>Expense Tracker</h2>
                <p style={{ color: "#666" }}>Login to continue</p>

                <input
                    placeholder="Email"
                    onChange={(e) => setEmail(e.target.value)}
                />
                <input
                    type="password"
                    placeholder="Password"
                    onChange={(e) => setPassword(e.target.value)}
                />

                <button style={{ width: "100%" }}>Login</button>
            </form>
        </div>
    );
}

const styles = {
    page: {
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
    },
    card: {
        width: "360px",
        padding: "24px",
        background: "white",
        borderRadius: "10px",
        boxShadow: "0 10px 30px rgba(0,0,0,0.1)",
        display: "flex",
        flexDirection: "column",
        gap: "12px",
    },
};